package provided.variables;

import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.basics.Type;
import provided.variables.expr.Expression;

import java.util.ArrayList;

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
    public static Assignment parseAsmt(ArrayList<Token> tokens) {
        Type type = Type.parseType(tokens);
        String id = tokens.remove(0).getToken();

        // Validate
        if(!tokens.get(0).getToken().equals("="))
            throw new ParsingError("Syntax Error", "=", tokens.get(0));
        tokens.remove(0);   // pop '='
        Expression expr = Expression.parseExpression(tokens);
        EndStmt endStmt = EndStmt.parseEndStmt(tokens);

        return new Assignment(type, id, expr, endStmt);
    }

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public java.lang.String convertToJott() {
        if (type != null) {
            return this.type.convertToJott() + " " + id + " = " + this.expr.convertToJott() + this.endStmt.convertToJott();
        }
        return id + " = " + this.expr.convertToJott() + this.endStmt.convertToJott();
    }

    @Override
    public java.lang.String convertToJava(java.lang.String className) {
        return null;
    }

    @Override
    public java.lang.String convertToC() {
        return null;
    }

    @Override
    public java.lang.String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
