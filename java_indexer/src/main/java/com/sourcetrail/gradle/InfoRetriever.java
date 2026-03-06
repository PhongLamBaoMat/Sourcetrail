package com.sourcetrail.gradle;

import org.gradle.api.GradleException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfoRetriever {
    public static String getMainSrcDirs(String projectRootPath, String initScriptPath) {
        try {
            StringBuilder ret = new StringBuilder();

            List<String> srcDirs = getSrcDirs("printMainSrcDirs", projectRootPath, initScriptPath);
            for (int i = 0; i < srcDirs.size(); i++) {
                if (i != 0) {
                    ret.append(";");
                }
                ret.append(srcDirs.get(i));
            }

            return ret.toString();
        } catch (GradleException e) {
            return "[ERROR] " + e.getMessage();
        }
    }

    public static String getTestSrcDirs(String projectRootPath, String initScriptPath) {
        try {
            StringBuilder ret = new StringBuilder();

            List<String> srcDirs = getSrcDirs("printTestSrcDirs", projectRootPath, initScriptPath);
            for (int i = 0; i < srcDirs.size(); i++) {
                if (i != 0) {
                    ret.append(";");
                }
                ret.append(srcDirs.get(i));
            }

            return ret.toString();
        } catch (GradleException e) {
            return "[ERROR] " + e.getMessage();
        }
    }

    public static void copyCompileLibs(String projectRootPath, String initScriptPath, String targetPath) {
        executeTask(
                "copyCompileLibs",
                projectRootPath,
                initScriptPath,
                Collections.singletonList("-Psourcetrail_lib_path=" + targetPath));
    }

    public static void copyTestCompileLibs(String projectRootPath, String initScriptPath, String targetPath) {
        executeTask(
                "copyTestCompileLibs",
                projectRootPath,
                initScriptPath,
                Collections.singletonList("-Psourcetrail_lib_path=" + targetPath));
    }

    private static List<String> getSrcDirs(String taskName, String projectRootPath, String initScriptPath) {
        String output = executeTask(taskName, projectRootPath, initScriptPath, null);

        List<String> paths = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new StringReader(output))) {
            String line;
            while ((line = reader.readLine()) != null) {
                paths.add(line);
            }
        } catch (IOException e) {
            // TODO: LOGERROR
        }

        return paths;
    }

    private static String executeTask(
            String taskName, String projectRootPath, String initScriptPath, List<String> additionalArguments)
            throws GradleException {
        ProjectConnection connection =
                GradleConnector.newConnector().forProjectDirectory(new File(projectRootPath)).connect();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

        try {
            List<String> arguments = new ArrayList<>();
            arguments.add("--init-script");
            arguments.add(initScriptPath);
            arguments.add("-q");

            if (additionalArguments != null) {
                arguments.addAll(additionalArguments);
            }

            BuildLauncher Launcher = connection.newBuild()
                    .forTasks(taskName)
                    .withArguments(arguments)
                    .setStandardOutput(new PrintStream(outputStream))
                    .setStandardError(new PrintStream(errorStream));

            Launcher.run();
        } catch (Exception e) {
            // TODO: LOGERROR
        } finally {
            connection.close();
        }

        if (!errorStream.toString().isEmpty()) {
            throw new GradleException(errorStream.toString());
        }

        return outputStream.toString();
    }
}
