package provided.variables.basics;

import provided.JottTree;
import provided.Token;

public enum Type implements JottTree {
    INT("Integer"),
    BOOL("Boolean"),
    DOUBLE("Double"),
    ID(""),
    KEYWORD(""),
    STRING("String"),
    VOID("Void");

    public final String label;

    Type(String name) {
        this.label = name;
    }

    public String getLabel() {
        return label;
    }

    public static Type getType(Token token) {
        return switch (token.getToken()) {
            case "Integer" -> INT;
            case "Boolean" -> BOOL;
            case "String" -> STRING;
            case "Void" -> VOID;
            case "Double" -> DOUBLE;
            default -> null;
        };
    }

    @Override
    public String convertToJott() {
        return this.label;
    }

    @Override
    public String convertToJava(String className) {
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}

