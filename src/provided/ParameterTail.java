package provided;

import java.util.ArrayList;

/**
 * File: ParameterTail.java
 * Implementation of the Parameter Tail of the Jott Grammar
 *
 * @author Derek Garcia
 **/
public class ParameterTail implements JottTree{
    // todo add w/ Celeste's implementation
    // private final Expression expr;
    private final ParameterTail params_t;


    /**
     * Creates a new params_t object
     *
     * @param params_t Tail of the params_t object
     */
    // todo add w/ Celeste's implementation
    // private ParameterTail createParameter(Expression expr, ParameterTail params_t)
    private ParameterTail(ParameterTail params_t){
        // this.expr = expr;
        this.params_t = params_t;
    }

    /**
     * Parse params_t
     *
     * @param tokens Tokens to parse
     * @return new params_t object
     */
    public static ParameterTail parseParams_t(ArrayList<Token> tokens){

        // base case
        if(tokens.get(0).getToken().equals("]"))
            return null;

        tokens.remove(0);   // pop ","

        // Expression expr = Expression.createExpression(ArrayList<Token> tokens);
        ParameterTail params_t = ParameterTail.parseParams_t(tokens);

        // return new ParameterTail(expr, params_t);
        return new ParameterTail(params_t);
    }

    /**
     * Will output a string of this tree in Jott
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
