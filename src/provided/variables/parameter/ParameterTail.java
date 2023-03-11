package provided.variables.parameter;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.variables.expr.Expression;

import java.util.ArrayList;

/**
 * File: ParameterTail.java
 * Implementation of the Parameter Tail of the Jott Grammar
 *
 * @author Derek Garcia
 **/
public class ParameterTail implements JottTree {
    private final Expression expr;
    private final ParameterTail params_t;


    /**
     * Creates a new params_t object
     *
     * @param params_t Tail of the params_t object
     */
    private ParameterTail(Expression expr, ParameterTail params_t){
         this.expr = expr;
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

        // Validate
        if(!tokens.get(0).getToken().equals(","))
            throw new ParsingError("Syntax Error", ",", tokens.get(0));
        tokens.remove(0);   // pop ","

        Expression expr = Expression.parseExpression(tokens);
        ParameterTail params_t = ParameterTail.parseParams_t(tokens);

        return new ParameterTail(expr, params_t);


    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        // base case
        if (this.params_t == null)
            return  "";
        // else get rest of tail
        return ", " + this.params_t.convertToJott();
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
