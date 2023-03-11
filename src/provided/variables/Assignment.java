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

    static Type type;
    static EndStmt end_statement;
    static Expression expr;
    static EndStmt endStmt;

    /**
     * Creates new asmt object
     *
     * @param type variable type
     */
    private Assignment() {}
    public static String id;
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
        expr = Expression.parseExpression(tokens);
        endStmt = EndStmt.parseEndStmt(tokens);
        return new Assignment();
    }

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public java.lang.String convertToJott() {
         return this.type.convertToJott() + " " + id + " = " + this.expr.convertToJott() + this.end_statement.convertToJott();
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
