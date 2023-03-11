package provided.variables.parameter;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.variables.expr.Expression;

import java.util.ArrayList;

/**
 * File: Parameter.java
 * Implementation of the Parameter of the Jott Grammar
 *
 * @author Derek Garcia
 **/
public class Parameter implements JottTree {

    private final Expression expr;
    private final ParameterTail params_t;


    /**
     * Creates a new params object
     *
     * @param params_t Tail of the params object
     */
    private Parameter(Expression expr, ParameterTail params_t){
        this.expr = expr;
        this.params_t = params_t;
    }

    /**
     * Parse params
     *
     * @param tokens Tokens to parse
     * @return new params object
     */
    public static Parameter parseParams(ArrayList<Token> tokens) {
        // Validate '['
        if(!tokens.get(0).getToken().equals("["))
            throw new ParsingError("Syntax Error", "[", tokens.get(0));
        tokens.remove(0);   // pop "["

        // parse expression
        Expression expr = Expression.parseExpression(tokens);
        // parse params_t
        ParameterTail params_t = ParameterTail.parseParams_t(tokens);   // will throw error if missing ","

        // Validate ']'
        if(!tokens.get(0).getToken().equals("["))
            throw new ParsingError("Syntax Error", "]", tokens.get(0));
        tokens.remove(0);   // pop "]"

        // Success
        return new Parameter(expr, params_t);

    }

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        return "[ " + this.expr.convertToJott() + this.params_t.convertToJott() + " ]";
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
