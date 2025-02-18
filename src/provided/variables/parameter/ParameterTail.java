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
    public static ParameterTail parseParams_t(ArrayList<Token> tokens, String functionCalling){
    
        // base case
        if(tokens.get(0).getToken().equals("]")) 
            return null;

        // Validate
        if(!tokens.get(0).getToken().equals(","))
            throw new ParsingError("Syntax Error", ",", tokens.get(0));
        tokens.remove(0);   // pop ","

        Expression expr = Expression.parseExpression(tokens, functionCalling);
        ParameterTail params_t = ParameterTail.parseParams_t(tokens, functionCalling);

        return new ParameterTail(expr, params_t);


    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        return ", " + this.expr.convertToJott();
    }

    @Override
    public String convertToJava(String className) {
        return "," + this.expr.convertToJava(className);
    }

    @Override
    public String convertToC() {
            return ", " + this.expr.convertToC();
    }

    @Override
    public String convertToPython() {
        return ", " + this.expr.convertToPython();
    }

    @Override
    public boolean validateTree() {
        if (params_t == null){
            return expr.validateTree();
        }
        return expr.validateTree() && params_t.validateTree();
    }

    public ArrayList<String> getTypes(){
        ArrayList<String> types = new ArrayList<>();
        if (params_t == null){
            types.add(expr.getType().type);
        }
        else {
            types.addAll(params_t.getTypes());
        }
        return types;
    }
}
