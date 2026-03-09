package com.sourcetrail;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class JavaIndexer {
    public static void processFile(
            int address,
            String filePath,
            String fileContent,
            String languageStandard,
            String classPath,
            int verbose) {
        processFile(
                new JavaIndexerAstVisitorClient(address),
                filePath,
                fileContent,
                languageStandard,
                classPath,
                verbose);
    }

    public static void processFile(
            AstVisitorClient astVisitorClient,
            String filePath,
            String fileContent,
            String languageStandard,
            String classPath,
            int verbose) {
        try {
            astVisitorClient.logInfo("indexing source file: " + filePath);

            Path path = Paths.get(filePath);

            ASTParser parser = ASTParser.newParser(AST.JLS25);

            parser.setResolveBindings(
                    true);      // solve "bindings" like the declaration of the type used in a var decl
            parser.setKind(
                    ASTParser.K_COMPILATION_UNIT);      // specify to parse the entire compilation unit
            parser.setBindingsRecovery(
                    true);      // also return bindings that are not resolved completely
            parser.setStatementsRecovery(true);

            {
                String convertedLanguageStandard = convertLanguageStandard(languageStandard);
                astVisitorClient.logInfo("using language standard " + convertedLanguageStandard);

                Hashtable<String, String> options = JavaCore.getOptions();
                options.put(JavaCore.COMPILER_PB_ENABLE_PREVIEW_FEATURES, JavaCore.DISABLED);
                options.put(JavaCore.COMPILER_PB_REPORT_PREVIEW_FEATURES, JavaCore.IGNORE);
                options.put(JavaCore.COMPILER_SOURCE, convertedLanguageStandard);
                options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, convertedLanguageStandard);
                options.put(JavaCore.COMPILER_COMPLIANCE, convertedLanguageStandard);
                parser.setCompilerOptions(options);
            }

            parser.setUnitName(path.getFileName().toString());

            List<String> classpath = new ArrayList<>();
            List<String> sources = new ArrayList<>();

            for (String classPathEntry : classPath.split("\\;")) {
                if (classPathEntry.endsWith(".jar")) {
                    classpath.add(classPathEntry);
                } else if (classPathEntry.endsWith(".aar")) {
                    File extractedJarFile = extractClassesJarFileFromAarFile(
                            Paths.get(classPathEntry), astVisitorClient);
                    if (extractedJarFile != null) {
                        classpath.add(extractedJarFile.getAbsolutePath());
                    }
                } else if (!classPathEntry.isEmpty()) {
                    sources.add(classPathEntry);
                }
            }

            parser.setEnvironment(
                    classpath.toArray(new String[0]), sources.toArray(new String[0]), null, true);
            parser.setSource(fileContent.toCharArray());

            CompilationUnit cu = (CompilationUnit) parser.createAST(null);

            ASTVisitor visitor;
            if (verbose != 0) {
                visitor = new VerboseContextAwareAstVisitor(
                        astVisitorClient, path.toFile(), fileContent, cu);
            } else {
                visitor = new ContextAwareAstVisitor(
                        astVisitorClient, path.toFile(), fileContent, cu);
            }

            astVisitorClient.logInfo("starting AST traversal");

            cu.accept(visitor);

            for (IProblem problem : cu.getProblems()) {
                if (problem.isError()) {
                    Range range = new Range(
                            cu.getLineNumber(problem.getSourceStart()),
                            cu.getColumnNumber(problem.getSourceStart() + 1),
                            cu.getLineNumber(problem.getSourceEnd()),
                            cu.getColumnNumber(problem.getSourceEnd()) + 1);

                    astVisitorClient.recordError(problem.getMessage(), false, true, range);
                }
            }

            for (Object commentObject : cu.getCommentList()) {
                if ((commentObject instanceof LineComment) || (commentObject instanceof BlockComment)) {
                    ((Comment) commentObject).accept(visitor);
                }
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            astVisitorClient.logError(sw.toString());
        }
    }

    public static String getPackageName(String fileContent) {
        String packageName = "";

        ASTParser parser = ASTParser.newParser(AST.JLS25);
        parser.setKind(
                ASTParser.K_COMPILATION_UNIT);      // specify to parse the entire compilation unit
        parser.setSource(fileContent.toCharArray());
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        PackageDeclaration packageDeclaration = cu.getPackage();
        if (packageDeclaration != null) {
            packageName = packageDeclaration.getName().getFullyQualifiedName();
        }
        return packageName;
    }

    public static void clearCaches() {
        Runtime.getRuntime().gc();
    }

    private static String convertLanguageStandard(String s) {
        return switch (s) {
            case "1" -> JavaCore.VERSION_1_1;
            case "2" -> JavaCore.VERSION_1_2;
            case "3" -> JavaCore.VERSION_1_3;
            case "4" -> JavaCore.VERSION_1_4;
            case "5" -> JavaCore.VERSION_1_5;
            case "6" -> JavaCore.VERSION_1_6;
            case "7" -> JavaCore.VERSION_1_7;
            case "8" -> JavaCore.VERSION_1_8;
            case "9" -> JavaCore.VERSION_9;
            case "10" -> JavaCore.VERSION_10;
            case "11" -> JavaCore.VERSION_11;
            case "12" -> JavaCore.VERSION_12;
            case "13" -> JavaCore.VERSION_13;
            case "14" -> JavaCore.VERSION_14;
            case "15" -> JavaCore.VERSION_15;
            case "16" -> JavaCore.VERSION_16;
            case "17" -> JavaCore.VERSION_17;
            case "18" -> JavaCore.VERSION_18;
            case "19" -> JavaCore.VERSION_19;
            case "20" -> JavaCore.VERSION_20;
            case "21" -> JavaCore.VERSION_21;
            case "22" -> JavaCore.VERSION_22;
            case "23" -> JavaCore.VERSION_23;
            case "24" -> JavaCore.VERSION_24;
            default -> JavaCore.VERSION_25;
        };
    }

    private static File extractClassesJarFileFromAarFile(
            Path aarFilePath, AstVisitorClient astVisitorClient) throws IOException {
        JarFile jarFile = new JarFile(aarFilePath.toString());
        ZipEntry classesJarEntry = jarFile.getEntry("classes.jar");
        if (classesJarEntry != null) {
            InputStream inputStream = jarFile.getInputStream(classesJarEntry);
            File tempFile = File.createTempFile(
                    "jar_file_from_" + Utility.getFilenameWithoutExtension(aarFilePath) + "_", ".jar");
            tempFile.deleteOnExit();

            byte[] buffer = new byte[8 * 1024];

            try {
                try (OutputStream output = Files.newOutputStream(tempFile.toPath())) {
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
            } finally {
                inputStream.close();
            }

            astVisitorClient.logInfo(
                    "Extracted classes.jar file from \"" + aarFilePath + "\" to \"" +
                            tempFile.getAbsolutePath() + "\". "
                            + "This file will be automatically deleted when the session ends.");

            return tempFile;
        } else {
            astVisitorClient.logError(
                    "Classpath entry \"" + aarFilePath +
                            "\" is malformed. No internal \"classes.jar\" entry could be found.");
        }
        jarFile.close();

        return null;
    }

    // the following methods are defined in the native c++ code

    static public native boolean getInterrupted(int address);

    static public native void logInfo(int address, String info);

    static public native void logWarning(int address, String warning);

    static public native void logError(int address, String error);

    static public native void recordSymbol(
            int address, String symbolName, int symbolType, int access, int definitionKind);

    static public native void recordSymbolWithLocation(
            int address,
            String symbolName,
            int symbolType,
            int beginLine,
            int beginColumn,
            int endLine,
            int endColumn,
            int access,
            int definitionKind);

    static public native void recordSymbolWithLocationAndScope(
            int address,
            String symbolName,
            int symbolType,
            int beginLine,
            int beginColumn,
            int endLine,
            int endColumn,
            int scopeBeginLine,
            int scopeBeginColumn,
            int scopeEndLine,
            int scopeEndColumn,
            int access,
            int definitionKind);

    static public native void recordSymbolWithLocationAndScopeAndSignature(
            int address,
            String symbolName,
            int symbolType,
            int beginLine,
            int beginColumn,
            int endLine,
            int endColumn,
            int scopeBeginLine,
            int scopeBeginColumn,
            int scopeEndLine,
            int scopeEndColumn,
            int signatureBeginLine,
            int signatureBeginColumn,
            int signatureEndLine,
            int signatureEndColumn,
            int access,
            int definitionKind);

    static public native void recordReference(
            int address,
            int referenceKind,
            String referencedName,
            String contextName,
            int beginLine,
            int beginColumn,
            int endLine,
            int endColumn);

    static public native void recordQualifierLocation(
            int address, String qualifierName, int beginLine, int beginColumn, int endLine, int endColumn);

    static public native void recordLocalSymbol(
            int address, String symbolName, int beginLine, int beginColumn, int endLine, int endColumn);

    static public native void recordComment(
            int address, int beginLine, int beginColumn, int endLine, int endColumn);

    static public native void recordError(
            int address,
            String message,
            int fatal,
            int indexed,
            int beginLine,
            int beginColumn,
            int endLine,
            int endColumn);
}