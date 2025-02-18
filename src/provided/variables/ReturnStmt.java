 package provided.variables;

import provided.*;
import provided.variables.basics.Type;
import provided.variables.expr.Expression;

import java.util.ArrayList;

public class ReturnStmt implements JottTree {

    EndStmt endStmt;
    Expression expression;
    int numIndents;

    public ReturnStmt(Expression expression, EndStmt endStmt, int numIndent){
        this.endStmt = endStmt;
        this.expression = expression;
        this.numIndents = numIndent;
    }

    public static ReturnStmt parseReturnStmt(ArrayList<Token> tokens, int numIndent, String functionCalling){
        // Use symTable for check if the return 
        tokens.remove(0);
        Expression expression = Expression.parseExpression(tokens, functionCalling);
        EndStmt endStmt = EndStmt.parseEndStmt(tokens);
        return new ReturnStmt(expression, endStmt, numIndent);
    }

    public String getIndents() {
        String indents = "";
        for (int i=0; i<this.numIndents; i++) {
            indents += "\t";
        }
        return indents;
    }

    @Override
    public String convertToJott() {
        if (expression != null) return "return " + expression.convertToJott() + endStmt.convertToJott();
        return "return" + endStmt.convertToJott();
    }

    @Override
    public String convertToJava(String className) {
        if (expression != null) return "return " + expression.convertToJava(className) + endStmt.convertToJava(className);
        return "return" + endStmt.convertToJava(className);
    }

    @Override
    public String convertToC() {
        return getIndents() + "return " + (expression != null ? expression.convertToC() : "") + endStmt.convertToC() + "\n";
    }

    @Override
    public String convertToPython() {
        if (expression != null) return getIndents() + "return " + expression.convertToPython() + endStmt.convertToPython();
        return getIndents() + "return" + endStmt.convertToPython();
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
