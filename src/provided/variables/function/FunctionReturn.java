package provided.variables.function;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.variables.basics.Type;

import java.util.ArrayList;

/**
 * File: FunctionReturn.java
 * Implementation of FunctionReturn of the Jott Grammar
 *
 * @author Derek Garcia
 **/
public class FunctionReturn implements JottTree {
    public static Type type;
    public static String typeString;

    /**
     * Creates new function_return object
     *
     * @param type return type
     */
    private FunctionReturn(){}

    /**
     * Parse function_return
     *
     * @param tokens Tokens to parse
     * @return new function_return object
     */
    public static FunctionReturn parseFunctionReturn(ArrayList<Token> tokens){
        type = Type.parseType(tokens);
        if (type == null) {
            if (tokens.get(0).getToken().equals("Void")) {
                typeString = "Void";
            } else {
                throw new ParsingError("Syntax Error", "Return Type", tokens.get(0));
            }
        } else {
            typeString = type.convertToJott();
        }

        tokens.remove(0);
        return new FunctionReturn();
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        return typeString;
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
