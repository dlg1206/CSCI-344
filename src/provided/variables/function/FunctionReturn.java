package provided.variables.function;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.variables.basics.Type;
import provided.variables.basics.Type;

import java.util.ArrayList;

/**
 * File: FunctionReturn.java
 * Implementation of FunctionReturn of the Jott Grammar
 *
 * @author Derek Garcia
 * @author Zoe Wheatcroft
 **/
public class FunctionReturn implements JottTree {
    public String returnType;

    /**
     * Creates new function_return object
     *
     * @param type return type
     */
    private FunctionReturn(String returnType) {
        this.returnType = returnType;
     }

    /**
     * Parse function_return
     *
     * @param tokens Tokens to parse
     * @return new function_return object
     */
    public static FunctionReturn parseFunctionReturn(ArrayList<Token> tokens){
        Type type = Type.parseType(tokens);
        String typeString = "";
        if (type == null) {
            if (tokens.get(0).getToken().equals("Void")) {
                typeString = "Void";
                tokens.remove(0);
            } else {
                throw new ParsingError("Syntax Error", "Return Type", tokens.get(0));
            }
        } else {
            typeString = type.convertToJott();
        }

        return new FunctionReturn(typeString);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        return returnType;
    }

    @Override
    public String convertToJava(String className) {
        return returnType;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return returnType;
    }

    @Override
    public boolean validateTree() {
        //make the string object into a type 
        Type type = new Type(returnType);
        //check that the return type is valid
        if(!type.validateTree()){return false;}

        return true;
    }
}
