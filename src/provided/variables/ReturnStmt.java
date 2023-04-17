 package provided.variables;

import provided.*;
import provided.variables.basics.Type;
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
        // Use symTable for check if the return 
        tokens.remove(0);
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
        if (expression != null) return "return " + expression.convertToPython() + endStmt.convertToPython();
        return "return" + endStmt.convertToPython();
    }

    @Override
    public boolean validateTree() {
        if(expression.validateTree() && endStmt.validateTree()){
            return true;
        }
        return false;
    }

    public Type isReturnable(){
        return expression.getType();
    }
}
