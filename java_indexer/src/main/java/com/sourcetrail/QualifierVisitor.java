package com.sourcetrail;

import com.sourcetrail.name.DeclName;
import com.sourcetrail.name.NameHierarchy;
import com.sourcetrail.name.TypeName;
import com.sourcetrail.name.resolver.BindingNameResolver;
import org.eclipse.jdt.core.dom.*;

import java.io.File;

public class QualifierVisitor {
    final private File m_filePath;
    final private CompilationUnit m_compilationUnit;
    final private boolean m_recordSymbolKinds;
    protected AstVisitorClient m_client;

    public QualifierVisitor(
            AstVisitorClient client, File filePath, CompilationUnit compilationUnit, boolean recordSymbolKinds) {
        m_client = client;
        m_filePath = filePath;
        m_compilationUnit = compilationUnit;
        m_recordSymbolKinds = recordSymbolKinds;
    }

    public void recordQualifierOfNode(ImportDeclaration node) {
        if (node != null && node.getName() instanceof QualifiedName) {
            recordNodeAsQualifier(((QualifiedName) node.getName()).getQualifier());
        }
    }

    public void recordQualifierOfNode(SimpleType node) {
        if (node != null) {
            if (node.getName() instanceof QualifiedName) {
                recordNodeAsQualifier(((QualifiedName) node.getName()).getQualifier());
            }
        }
    }

    public void recordQualifierOfNode(QualifiedType node) {
        if (node != null) {
            recordNodeAsQualifier(node.getQualifier());
        }
    }

    public void recordQualifierOfNode(NameQualifiedType node) {
        if (node != null) {
            recordNodeAsQualifier(node.getQualifier());
        }
    }

    public void recordQualifierOfNode(QualifiedName node) {
        if (node != null) {
            recordNodeAsQualifier(node.getQualifier());
        }
    }

    public void recordQualifierOfNode(FieldAccess node, FileContent fileContent) {
        if (node != null) {
            recordNodeAsQualifier(node.getExpression(), fileContent);
        }
    }

    public void recordQualifierOfNode(SuperFieldAccess node, FileContent fileContent) {
        if (node != null) {
            recordNodeAsQualifier(node, fileContent);
        }
    }

    public void recordQualifierOfNode(ThisExpression node, FileContent fileContent) {
        if (node != null) {
            recordNodeAsQualifier(node.getQualifier());
        }
    }

    public void recordQualifierOfNode(MethodInvocation node, FileContent fileContent) {
        if (node != null) {
            recordNodeAsQualifier(node.getExpression(), fileContent);
        }
    }

    public void recordQualifierOfNode(SuperMethodInvocation node, FileContent fileContent) {
        if (node != null) {
            IMethodBinding methodBinding = node.resolveMethodBinding();
            if (methodBinding != null) {
                ITypeBinding typeBinding = methodBinding.getDeclaringClass();
                if (typeBinding != null) {
                    m_client.recordQualifierLocation(
                            BindingNameResolver
                                    .getQualifiedName(typeBinding, m_filePath, m_compilationUnit)
                                    .orElse(TypeName.unsolved())
                                    .toDeclName()
                                    .toNameHierarchy(),
                            fileContent.findRange("super", getRange(node).begin));
                }
            }

            recordNodeAsQualifier(node.getQualifier());
        }
    }

    public void recordQualifierOfNode(CreationReference node) {
        if (node != null) {
            recordNodeAsQualifier(node.getType());
        }
    }

    public void recordQualifierOfNode(ExpressionMethodReference node, FileContent fileContent) {
        if (node != null) {
            recordNodeAsQualifier(node.getExpression(), fileContent);
        }
    }

    public void recordQualifierOfNode(SuperMethodReference node, FileContent fileContent) {
        if (node != null) {
            IMethodBinding methodBinding = node.resolveMethodBinding();
            if (methodBinding != null) {
                ITypeBinding typeBinding = methodBinding.getDeclaringClass();
                if (typeBinding != null) {
                    m_client.recordQualifierLocation(
                            BindingNameResolver
                                    .getQualifiedName(typeBinding, m_filePath, m_compilationUnit)
                                    .orElse(TypeName.unsolved())
                                    .toDeclName()
                                    .toNameHierarchy(),
                            fileContent.findRange("super", getRange(node).begin));
                }
            }

            recordNodeAsQualifier(node.getQualifier());
        }
    }

    public void recordQualifierOfNode(TypeMethodReference node) {
        if (node != null) {
            recordNodeAsQualifier(node.getType());
        }
    }


    private void recordNodeAsQualifier(Expression node, FileContent fileContent) {
        if (node != null) {
            switch (node) {
                case Name name -> recordNodeAsQualifier(name);
                case SuperFieldAccess expression -> {
                    IVariableBinding fieldBinding = expression.resolveFieldBinding();

                    if (fieldBinding != null) {
                        m_client.recordQualifierLocation(
                                BindingNameResolver
                                        .getQualifiedName(
                                                fieldBinding.getDeclaringClass(), m_filePath, m_compilationUnit)
                                        .orElse(TypeName.unsolved())
                                        .toDeclName()
                                        .toNameHierarchy(),
                                fileContent.findRange(
                                        "super",
                                        expression.getQualifier() != null ? getRange(expression.getQualifier()).end
                                                : getRange(expression).begin));
                    }

                    recordNodeAsQualifier(expression.getQualifier());
                }
                case ThisExpression expression -> {

                    m_client.recordQualifierLocation(
                            BindingNameResolver
                                    .getQualifiedName(
                                            expression.resolveTypeBinding(), m_filePath, m_compilationUnit)
                                    .orElse(TypeName.unsolved())
                                    .toDeclName()
                                    .toNameHierarchy(),
                            fileContent.findRange(
                                    "this",
                                    expression.getQualifier() != null ? getRange(expression.getQualifier()).end
                                            : getRange(expression).begin));

                    recordNodeAsQualifier(expression.getQualifier());
                }
                default -> {
                }
            }
        }
    }

    private void recordNodeAsQualifier(Type node) {
        if (node != null) {
            switch (node) {
                case SimpleType simpleType -> recordNodeAsQualifier(simpleType.getName());
                case QualifiedType qualifiedType -> {
                    recordNodeAsQualifier(qualifiedType.getName());
                    recordNodeAsQualifier(qualifiedType.getQualifier());
                }
                case NameQualifiedType nameQualifiedType -> {
                    recordNodeAsQualifier(nameQualifiedType.getName());
                    recordNodeAsQualifier(nameQualifiedType.getQualifier());
                }
                default -> {
                }
            }
        }
    }

    private void recordNodeAsQualifier(Name node) {
        if (node != null) {
            IBinding binding = node.resolveBinding();
            if (binding instanceof ITypeBinding) {
                Range range = getRange(node);
                if (node instanceof QualifiedName) {
                    range = getRange(((QualifiedName) node).getName());
                }

                m_client.recordQualifierLocation(
                        BindingNameResolver
                                .getQualifiedName((ITypeBinding) binding, m_filePath, m_compilationUnit)
                                .orElse(TypeName.unsolved())
                                .toDeclName()
                                .toNameHierarchy(),
                        range);
            } else if (binding instanceof IPackageBinding) {
                Range range = getRange(node);
                if (node instanceof QualifiedName) {
                    range = getRange(((QualifiedName) node).getName());
                }

                NameHierarchy symbolName =
                        BindingNameResolver
                                .getQualifiedName((IPackageBinding) binding, m_filePath, m_compilationUnit)
                                .orElse(DeclName.unsolved())
                                .toNameHierarchy();

                if (m_recordSymbolKinds) {
                    m_client.recordSymbol(
                            symbolName, SymbolKind.PACKAGE, AccessKind.NONE, DefinitionKind.NONE);
                }

                m_client.recordQualifierLocation(symbolName, range);
            }

            if (node instanceof QualifiedName) {
                recordNodeAsQualifier(((QualifiedName) node).getQualifier());
            }
        }
    }

    private Range getRange(ASTNode node) {
        return Utility.getRange(node, m_compilationUnit);
    }
}
