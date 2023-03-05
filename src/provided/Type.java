package provided;

import java.util.ArrayList;

/**
 * File: Type.java
 * Implementation of Type of the Jott Grammar
 *
 * @author Derek Garcia
 **/
public class Type implements JottTree {

    /**
     * Enums to used to convert to target language
     */
    private enum TYPE {
        DOUBLE,
        INTEGER,
        STRING,
        BOOLEAN,
        VOID
    }

    private final TYPE type;

    /**
     * Creates new Type object
     *
     * @param type type of type enum
     */
    private Type(TYPE type) {
        this.type = type;
    }


    /**
     * Parse Type
     *
     * @param tokens Tokens to parse
     * @return new type object
     */
    public static Type parseType(ArrayList<Token> tokens) {
        Token token = tokens.remove(0); // pop list
        // Convert to enum
        switch (token.getToken()) {
            case "Double" -> {
                return new Type(TYPE.DOUBLE);
            }
            case "Integer" -> {
                return new Type(TYPE.INTEGER);
            }
            case "String" -> {
                return new Type(TYPE.STRING);
            }
            case "Boolean" -> {
                return new Type(TYPE.BOOLEAN);
            }
            case "Void" -> {
                return new Type(TYPE.VOID);
            }
            // Push back onto list if not a type
            default -> {
                tokens.add(0, token);
                return null;
            }
        }
    }

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        return null;
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
