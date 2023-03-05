package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class Parameter implements JottTree{


    // Temp testing funct
    public static void main(String[] args) throws Exception {
        ArrayList<Token> params = new ArrayList<>();
        params.add(new Token("[", "foo", 0, TokenType.L_BRACKET));
        params.add(new Token("5", "foo", 0, TokenType.NUMBER));
        params.add(new Token(",", "foo", 0, TokenType.COMMA));
        params.add(new Token("7", "foo", 0, TokenType.NUMBER));
        params.add(new Token("]", "foo", 0, TokenType.R_BRACKET));
        parseParams(params);
    }
    // todo add w/ Celeste's implementation
    // private final Expression expr;
    private final ParameterTail params_t;


    // todo add w/ Celeste's implementation
    // private Parameter createParameter(Expression expr, ParameterTail params_t)
    private Parameter(ParameterTail params_t){
        // this.expr = createExpression
        this.params_t = params_t;

    }

    public static Parameter parseParams(ArrayList<Token> tokens){
        tokens.remove(0);   // pop "["
        // Expression expr = Expression.createExpression(tokens);
        ParameterTail params_t = ParameterTail.parseParams_t(tokens);
        tokens.remove(0);   // pop "]"
        // return new Parameter(expr, params_t)
        return new Parameter(params_t);
    }

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
