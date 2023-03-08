package provided.variables.function;

import provided.JottTree;
import provided.Token;
import provided.Type;

import java.util.ArrayList;

/**
 * File: FunctionReturn.java
 * Implementation of FunctionReturn of the Jott Grammar
 *
 * @author Derek Garcia
 **/
public class FunctionReturn implements JottTree {
    private final Type type;

    /**
     * Creates new function_return object
     *
     * @param type return type
     */
    private FunctionReturn(Type type){
        this.type = type;
    }

    /**
     * Parse function_return
     *
     * @param tokens Tokens to parse
     * @return new function_return object
     */
    public static FunctionReturn parseFunction_return(ArrayList<Token> tokens){
        return new FunctionReturn(Type.parseType(tokens));
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        return this.type.convertToJott();
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
