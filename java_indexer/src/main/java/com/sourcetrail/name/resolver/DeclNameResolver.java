package com.sourcetrail.name.resolver;

import com.sourcetrail.ContextList;
import com.sourcetrail.Position;
import com.sourcetrail.Utility;
import com.sourcetrail.name.DeclName;
import com.sourcetrail.name.FunctionDeclName;
import com.sourcetrail.name.TypeName;
import com.sourcetrail.name.VariableDeclName;
import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeclNameResolver extends NameResolver {
    public DeclNameResolver(File currentFile, CompilationUnit compilationUnit, ContextList ignoredContexts) {
        super(currentFile, compilationUnit, ignoredContexts);
    }

    public static DeclName getQualifiedDeclName(
            VariableDeclarationFragment decl, File currentFile, CompilationUnit compilationUnit) {
        return getQualifiedDeclName(decl, currentFile, compilationUnit, null);
    }

    public static DeclName getQualifiedDeclName(
            VariableDeclarationFragment decl,
            File currentFile,
            CompilationUnit compilationUnit,
            ContextList ignoredContexts) {
        DeclNameResolver resolver = new DeclNameResolver(
                currentFile, compilationUnit, ignoredContexts);
        return resolver.getQualifiedDeclName(decl);
    }

    public static DeclName getQualifiedDeclName(
            BodyDeclaration decl, File currentFile, CompilationUnit compilationUnit) {
        return getQualifiedDeclName(decl, currentFile, compilationUnit, null);
    }

    public static DeclName getQualifiedDeclName(
            BodyDeclaration decl,
            File currentFile,
            CompilationUnit compilationUnit,
            ContextList ignoredContexts) {
        DeclNameResolver resolver = new DeclNameResolver(
                currentFile, compilationUnit, ignoredContexts);
        return resolver.getQualifiedDeclName(decl);
    }

    public static DeclName getQualifiedDeclName(
            AnonymousClassDeclaration decl, File currentFile, CompilationUnit compilationUnit) {
        return getQualifiedDeclName(decl, currentFile, compilationUnit, null);
    }

    public static DeclName getQualifiedDeclName(
            AnonymousClassDeclaration decl,
            File currentFile,
            CompilationUnit compilationUnit,
            ContextList ignoredContexts) {
        DeclNameResolver resolver = new DeclNameResolver(
                currentFile, compilationUnit, ignoredContexts);
        return resolver.getQualifiedDeclName(decl);
    }

    public static DeclName getQualifiedName(Name name) {
        if (name.isSimpleName()) {
            return new DeclName(((SimpleName) name).getIdentifier());
        } else {
            DeclName declName = new DeclName(((QualifiedName) name).getName().getIdentifier());
            declName.setParent(getQualifiedName(((QualifiedName) name).getQualifier()));
            return declName;
        }
    }

    public DeclName getQualifiedDeclName(VariableDeclarationFragment decl) {
        DeclName declName = DeclName.unsolved();

        if (decl != null) {
            declName = getDeclName(decl);
            if (!declName.getIsUnsolved()) {
                DeclName parentDeclName = getQualifiedContextName(decl);
                if (parentDeclName != null) {
                    if (!parentDeclName.getIsUnsolved()) {
                        declName.setParent(parentDeclName);
                    } else {
                        declName = DeclName.unsolved();
                    }
                }
            }
        }
        return declName;
    }

    public DeclName getDeclName(VariableDeclarationFragment decl) {
        TypeName typeName = TypeName.unsolved();

        boolean isStatic = false;
        Optional<FieldDeclaration> fieldDeclaration = getAncestorOfType(decl, FieldDeclaration.class);
        if (fieldDeclaration.isPresent()) {
            if (decl.resolveBinding() != null) {
                isStatic = Modifier.isStatic(decl.resolveBinding().getModifiers());
            } else {
                isStatic = Modifier.isStatic(fieldDeclaration.get().getModifiers());
            }
            typeName = BindingNameResolver
                    .getQualifiedName(
                            fieldDeclaration.get().getType().resolveBinding(),
                            m_currentFile,
                            m_compilationUnit,
                            m_ignoredContexts.copy())
                    .orElse(TypeName.unsolved());
        }

        return new VariableDeclName(decl.getName().getIdentifier(), typeName, isStatic);
    }

    public DeclName getQualifiedDeclName(BodyDeclaration decl) {
        DeclName declName = DeclName.unsolved();

        if (decl != null) {
            declName = getDeclName(decl);
            if (!declName.getIsUnsolved()) {
                DeclName parentDeclName = getQualifiedContextName(decl);
                if (parentDeclName != null) {
                    if (!parentDeclName.getIsUnsolved()) {
                        declName.setParent(parentDeclName);
                    } else {
                        declName = DeclName.unsolved();
                    }
                }
            }
        }

        return declName;
    }

    public DeclName getDeclName(BodyDeclaration decl) {
        DeclName declName = DeclName.unsolved();

        if (decl != null) {
            switch (decl) {
                case AnnotationTypeDeclaration annotationTypeDeclaration ->
                        declName = new DeclName(annotationTypeDeclaration.getName().getIdentifier());
                case EnumDeclaration enumDeclaration ->
                        declName = new DeclName(enumDeclaration.getName().getIdentifier());
                case TypeDeclaration typeDeclaration -> {

                    List<String> typeParameterNames = new ArrayList<>();
                    for (Object typeParameter : typeDeclaration.typeParameters()) {
                        if (typeParameter instanceof TypeParameter) {
                            typeParameterNames.add(
                                    ((TypeParameter) typeParameter).getName().getIdentifier());
                        }
                    }

                    declName = new DeclName(
                            typeDeclaration.getName().getIdentifier(), typeParameterNames);
                }
                case AnnotationTypeMemberDeclaration annotationTypeMemberDeclaration -> declName = new DeclName(
                        annotationTypeMemberDeclaration.getName().getIdentifier());
                case EnumConstantDeclaration enumConstantDeclaration ->
                        declName = new DeclName(enumConstantDeclaration.getName().getIdentifier());
                case FieldDeclaration fieldDeclaration -> declName = new DeclName("FieldDeclaration");
                case Initializer initializer -> declName = new DeclName("Initializer");
                case MethodDeclaration methodDeclaration -> {

                    List<String> typeParameterNames = new ArrayList<>();
                    for (Object typeParameter : methodDeclaration.typeParameters()) {
                        if (typeParameter instanceof TypeParameter) {
                            typeParameterNames.add(
                                    ((TypeParameter) typeParameter).getName().getIdentifier());
                        }
                    }

                    ContextList ignoredContexts = m_ignoredContexts.copy();
                    ignoredContexts.add(methodDeclaration.resolveBinding());

                    TypeName returnTypeName = methodDeclaration.isConstructor()
                            ? null
                            : BindingNameResolver
                            .getQualifiedName(
                                    methodDeclaration.getReturnType2().resolveBinding(),
                                    m_currentFile,
                                    m_compilationUnit,
                                    ignoredContexts)
                            .orElse(TypeName.unsolved());

                    List<TypeName> parameterTypeNames = new ArrayList<>();
                    for (Object parameter : methodDeclaration.parameters()) {
                        if (parameter instanceof SingleVariableDeclaration) {
                            parameterTypeNames.add(
                                    BindingNameResolver
                                            .getQualifiedName(
                                                    ((SingleVariableDeclaration) parameter).getType().resolveBinding(),
                                                    m_currentFile,
                                                    m_compilationUnit,
                                                    ignoredContexts)
                                            .orElse(TypeName.unsolved()));
                        }
                    }

                    declName = new FunctionDeclName(
                            methodDeclaration.getName().getIdentifier(),
                            typeParameterNames,
                            returnTypeName,
                            parameterTypeNames,
                            Modifier.isStatic(methodDeclaration.getModifiers()));
                }
                default -> {
                }
            }
        }

        return declName;
    }

    public DeclName getQualifiedDeclName(AnonymousClassDeclaration decl) {
        DeclName declName = DeclName.unsolved();

        if (decl != null) {
            declName = getDeclName(decl);
            if (!declName.getIsUnsolved()) {
                DeclName parentDeclName = getQualifiedContextName(decl);
                if (parentDeclName != null) {
                    if (!parentDeclName.getIsUnsolved()) {
                        declName.setParent(parentDeclName);
                    } else {
                        declName = DeclName.unsolved();
                    }
                }
            }
        }
        return declName;
    }

    public DeclName getDeclName(AnonymousClassDeclaration decl) {
        Position pos = Utility.getRange(decl, m_compilationUnit).begin;
        return DeclName.anonymousClass(m_currentFile, pos.line, pos.column);
    }

    private DeclName getQualifiedContextName(ASTNode decl) {
        ASTNode parentNode = decl.getParent();
        switch (parentNode) {
            case null -> {
                return null;
            }
            case BodyDeclaration bodyDeclaration when !(parentNode instanceof FieldDeclaration) -> {
                return getQualifiedDeclName(bodyDeclaration);
            }
            case AnonymousClassDeclaration anonymousClassDeclaration -> {
                return getQualifiedDeclName(anonymousClassDeclaration);
            }
            case CompilationUnit compilationUnit -> {
                PackageDeclaration packageDecl = compilationUnit.getPackage();
                if (packageDecl != null) {
                    return getQualifiedName(packageDecl.getName());
                }
                return null;
            }
            default -> {
            }
        }

        return getQualifiedContextName(parentNode);
    }
}
