package provided.variables.basics;

import provided.Token;

public enum Type {
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
}

