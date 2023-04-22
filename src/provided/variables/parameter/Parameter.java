package provided.variables.parameter;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.variables.expr.Expression;

import java.util.ArrayDeque;
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
    public static Parameter parseParams(ArrayList<Token> tokens, String functionCalling) {
        // parse expression
        Expression expr = Expression.parseExpression(tokens, functionCalling);
        
        // 
        // parse params_t
        ParameterTail params_t = ParameterTail.parseParams_t(tokens, functionCalling);   // will throw error if missing ","

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
        if (this.params_t != null)
            return this.expr.convertToJott() + this.params_t.convertToJott();
        return this.expr.convertToJott();
    }

    @Override
    public String convertToJava(String className) {
        if (this.params_t != null)
            return this.expr.convertToJava(className) + this.params_t.convertToJava(className);
        return this.expr.convertToJava(className);
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        if (this.params_t != null)
            return this.expr.convertToPython() + this.params_t.convertToPython();
        return this.expr.convertToPython();
    }

    @Override
    public boolean validateTree() {

        System.out.println("GETTING TO PARAM");
        System.out.println(this.expr.convertToJott());
        // Check tail if needed exists
        if(this.params_t != null)
            return this.expr.validateTree() && this.params_t.validateTree();

        // else just test the expression
        return this.expr.validateTree();
    }

    public Expression getFirstParameter(){
        return expr;
    }

    public ParameterTail getParams_t(){
        return params_t;
    }

    public ArrayList<String> getTypes(){

        ArrayList<String> types = new ArrayList<>();
        types.add(expr.getType().type);
        for (String type : params_t.getTypes()){
            types.add(type);
        }

        return types;
    }
}
