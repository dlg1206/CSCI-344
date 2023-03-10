 package provided.variables;

import provided.*;
import provided.variables.expr.Expression;

import java.util.ArrayList;

public class ReturnStmt implements JottTree{

    private static EndStmt endStmt;
    private static  Expression expression;

    public ReturnStmt(Expression expression, EndStmt endStmt){
        this.endStmt = endStmt;
        this.expression = expression;
    }

    public static ReturnStmt parseReturnStmt(ArrayList<Token> tokens){
        if(tokens.get(0).getToken() == "return"){
            tokens.remove(0);
            //start feeding in the token string as an expression till we get ;
            expression = expression.createExpression(tokens);
            if(tokens.get(0).getToken() == ";"){
                endStmt = endStmt.parseEndStmt(tokens);
            }
            else{
                new ParsingError("Semicolon expected", ";", tokens.get(0));
                return null;
                //error, missing semicolon
            }
            return new ReturnStmt(expression, endStmt);
        }
        else{
            new ParsingError("Missing return", "return", tokens.get(0));
            return null;
        }
    }

    @Override
    public String convertToJott() {
        return "return" + expression.convertToJott() + endStmt.convertToJott();
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
