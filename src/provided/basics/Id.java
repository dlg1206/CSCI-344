package provided.basics;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;

public class Id implements JottTree {
    Token id;

    public Id() {

    }

    public static Id CreateId(ArrayList<Token> tokens) {
        Id id = new Id();
        Token token = tokens.remove(0);

        if (Character.isUpperCase(token.getToken().charAt(0))) {
//            throw new ParsingError(, token);
        }

        if (token.getToken().equals("elseif")) {
//            throw new ParsingError(, token);
        }

        if (token.getToken().equals("else")) {
//            throw new ParsingError(, token);
        }
        id.id = token;
        return id;


    }

    public Token getToken() {
        return id;
    }


    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        return id.getToken();
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