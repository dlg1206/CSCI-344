package provided.variables;

import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.basics.Type;
import provided.variables.expr.Expression;
import provided.symtable.SymTable;
import java.util.ArrayList;
import provided.symtable.Var;

/**
 * File: Assignment.java
 * Implementation of Assignment of the Jott Grammar
 *
 * @author Derek Garcia
 **/
public class Assignment extends Stmt {

    Type type;
    String id;
    Expression expr;
    EndStmt endStmt;
    static int lineNum;
    static String fileName;
    /**
     * Creates new asmt object
     *
     * @param type variable type
     */
    private Assignment(Type type, String id, Expression expr, EndStmt endStmt) {
        this.type = type;
        this.id = id;
        this.expr = expr;
        this.endStmt = endStmt;
    }
    public static Token currToken;
    /**
     * Parse asmt
     *
     * @param tokens Tokens to parse
     * @return new asmt object
     */
    public static Assignment parseAsmt(ArrayList<Token> tokens, String functionCalling) {
        fileName = tokens.get(0).getFilename();
        lineNum = tokens.get(0).getLineNum();
        
        Type type = Type.parseType(tokens);
        Token idToken = tokens.remove(0);
        
        String id = idToken.getToken();
        
        // Validate
        if(!tokens.get(0).getToken().equals("="))
            throw new ParsingError("Syntax Error", "=", tokens.get(0));
        tokens.remove(0);   // pop '='
        Expression expr = Expression.parseExpression(tokens, functionCalling);
        EndStmt endStmt = EndStmt.parseEndStmt(tokens);
        // Adding to Symbol Table
        if (type != null) {
            SymTable.addVar(id, type.type, idToken.getFilename(), idToken.getLineNum());
        }
        return new Assignment(type, id, expr, endStmt);
    }

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        if (type != null) {
            return this.type.convertToJott() + " " + id + " = " + this.expr.convertToJott() + this.endStmt.convertToJott();
        }
        return id + " = " + this.expr.convertToJott() + this.endStmt.convertToJott();
    }

    @Override
    public String convertToJava(java.lang.String className) {
        if (type != null) {
            return this.type.convertToJava(className) + " " + id + " = " + this.expr.convertToJava(className) + this.endStmt.convertToJava(className);
        }
        return id + " = " + this.expr.convertToJava(className) + this.endStmt.convertToJava(className);
    }



    @Override
    public String convertToC() {
        if(type != null){
            return this.type.convertToC() + " " + id + " = " + this.expr.convertToC() + this.endStmt.convertToC();
        }
        return id + " = " + this.expr.convertToC() + this.endStmt.convertToC();
    }

    @Override
    public String convertToPython() {
        return id + " = " + this.expr.convertToPython() + this.endStmt.convertToPython();
    }

    @Override
    public boolean validateTree() {
        Var var = SymTable.getVar(id);
        if (var == null) {
            String message = "Variable: " + id + " accessed prior to declaration";
            new ParsingError("Semantic Error", message, fileName, lineNum);
            return false;
        }
        String varType = var.getType();
        String exprType = expr.getType().type;
        if (!varType.equals(exprType)) {
            String message = "varType: " + varType + " := " + exprType + " is not a valid assignment";
            new ParsingError("Semantic Error", message, fileName, lineNum);
            return false;
        }
        return true;
    }
}
