package provided.variables;

import provided.ParsingError;
import provided.Token;
import provided.TokenType;

import provided.variables.basics.Id;
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
        type = Type.parseType(tokens);
        currToken = tokens.get(0);
        if (currToken.getTokenType() != TokenType.ID_KEYWORD) {
            throw new ParsingError("Syntax Error", "Id_Keyword", currToken);
        }
        id = currToken.getToken();
        tokens.remove(0);
        currToken = tokens.get(0);
        // Validate
        if(!currToken.getToken().equals("=")) {
            throw new ParsingError("Syntax Error", "=", currToken);
        }
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
    public String convertToJott() {
         return this.type.convertToJott() + " " + this.id.convertToJott() + " = "
                 + this.expr.convertToJott() + this.end_statement.convertToJott();
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
