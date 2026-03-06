package com.sourcetrail;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.File;

public class VerboseContextAwareAstVisitor extends ContextAwareAstVisitor {
    private String indentation = "";

    public VerboseContextAwareAstVisitor(
            AstVisitorClient client, File filePath, String fileContent, CompilationUnit compilationUnit) {
        super(client, filePath, fileContent, compilationUnit);
    }

    public void preVisit(ASTNode node) {
        Range range = getRange(node);
        m_client.logInfo(
                indentation + node.getClass() + "[" + range.begin.line + ":" +
                        range.begin.column + "|" + range.end.line + ":" + range.end.column + "]");
        indentation += "| ";
    }

    public void postVisit(ASTNode node) {
        indentation = indentation.substring(0, indentation.length() - 2);
    }
}
