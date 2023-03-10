package provided.variables;

import provided.*;
import provided.variables.expr.Expression;

import java.util.ArrayList;

public class ReturnStmt implements JottTree{

    private EndStmt endStmt;
    private Expression expression;

    public ReturnStmt(Expression expression, EndStmt endStmt){
        this.endStmt = endStmt;
        this.expression = expression;
    }

    public static ReturnStmt parseReturnStmt(ArrayList<Token> tokens){
        if(tokens.get(0).getToken() == "return"){
            tokens.remove(0);
            //start feeding in the token string as an expression till we get ;
            Expression expression = Expression.createExpression(tokens);
            if(tokens.get(0).getToken() == ";"){
                EndStmt endStmt = EndStmt.parseEndStmt(tokens);
            }
            else{
                //error, missing semicolon
            }
            // todo return new Returnstatement object
            return null;
        }
        else{
            return null;
            //error, no return
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
