package provided.variables.parameter;

import provided.JottTree;
import provided.Token;

import java.util.ArrayList;

/**
 * File: Parameter.java
 * Implementation of the Parameter of the Jott Grammar
 *
 * @author Derek Garcia
 **/
public class Parameter implements JottTree {

    // todo add w/ Celeste's implementation
    // private final Expression expr;
    private final ParameterTail params_t;


    /**
     * Creates a new params object
     *
     * @param params_t Tail of the params object
     */
    // todo add w/ Celeste's implementation
    // private Parameter createParameter(Expression expr, ParameterTail params_t)
    private Parameter(ParameterTail params_t) {
        // this.expr = expr;
        this.params_t = params_t;
    }

    /**
     * Parse params
     *
     * @param tokens Tokens to parse
     * @return new params object
     */
    public static Parameter parseParams(ArrayList<Token> tokens) {
        tokens.remove(0);   // pop "["
        // Expression expr = Expression.createExpression(tokens);
        ParameterTail params_t = ParameterTail.parseParams_t(tokens);
        tokens.remove(0);   // pop "]"
        // return new Parameter(expr, params_t)
        return new Parameter(params_t);
    }

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        // return "[ " + this.expr.convertToJott() + this.params_t.convertToJott() + " ]"
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
