 package provided.variables;

import provided.*;
import provided.variables.expr.Expression;

import java.util.ArrayList;

public class ReturnStmt implements JottTree {

    EndStmt endStmt;
    Expression expression;

    public ReturnStmt(Expression expression, EndStmt endStmt){
        this.endStmt = endStmt;
        this.expression = expression;
    }

    public static ReturnStmt parseReturnStmt(ArrayList<Token> tokens){
        tokens.remove(0);
        //start feeding in the token string as an expression till we get ;
        
        
        Expression expression = Expression.parseExpression(tokens);
        
        EndStmt endStmt = EndStmt.parseEndStmt(tokens);
        return new ReturnStmt(expression, endStmt);
    }

    @Override
    public String convertToJott() {
        if (expression != null) return "return " + expression.convertToJott() + endStmt.convertToJott();
        return "return" + endStmt.convertToJott();
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
