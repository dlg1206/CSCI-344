package provided.variables.basics;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class Constant implements JottTree {

    private Token token;
    private Type type;

    private Constant(Type type, Token token) {
        this.type = type;
        this.token = token;
    }

    public Type parseType() {
        return this.type;
    }

    public Token getToken() {
        return this.token;
    }

    public static Constant createConstant(ArrayList<Token> tokens) {
        if (tokens.isEmpty()) {
            // TODO: error handling
        }
        Token token = tokens.remove(0);
        if (token.getTokenType() == TokenType.NUMBER) {
            if (token.getToken().contains(".")) {
                return new Constant(Type.parseType(tokens), token);
            } else {
                return new Constant(Type.parseType(tokens), token);
            }
        } else if (token.getToken().contains("True") || token.getToken().contains("False")) {
            return new Constant(Type.parseType(tokens), token);
        } else {
            return new Constant(Type.parseType(tokens), token);
        }
    }

    public String getContents() {
        return token.getToken();
    }

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        return getContents();
    }

    /**
     * Will output a string of this tree in Java
     *
     * @param className
     * @return a string representing the Java code of this tree
     */
    @Override
    public String convertToJava(String className) {
        return null;
    }

    /**
     * Will output a string of this tree in C
     *
     * @return a string representing the C code of this tree
     */
    @Override
    public String convertToC() {
        return null;
    }

    /**
     * Will output a string of this tree in Python
     *
     * @return a string representing the Python code of this tree
     */
    @Override
    public String convertToPython() {
        return null;
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     *
     * @return true if valid Jott code; false otherwise
     */
    @Override
    public boolean validateTree() {
        return false;
    }
}