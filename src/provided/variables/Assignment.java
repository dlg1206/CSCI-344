package provided.variables;

import provided.ParsingError;
import provided.Token;
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

    private final Type type;
     private final Id id;
     private final Expression expr;
     private final EndStmt end_statement;


    /**
     * Creates new asmt object
     *
     * @param type variable type
     */
     private Assignment(Type type, Id id, Expression expr, EndStmt end_statement) {
        this.type = type;
        this.id = id;
        this.expr = expr;
        this.end_statement = end_statement;
    }

    /**
     * Parse asmt
     *
     * @param tokens Tokens to parse
     * @return new asmt object
     */
    public static Assignment parseAsmt(ArrayList<Token> tokens) {
        try{
            Type type = Type.getType(tokens.remove(0));
            Id id = Id.createId(tokens);

            // Validate
            if(!tokens.get(0).getToken().equals("="))
                throw new ParsingError("Syntax Error", "=", tokens.get(0));
            tokens.remove(0);   // pop '='

            Expression expr = Expression.createExpression(tokens);
            EndStmt end_statement = EndStmt.parseEndStmt(tokens);

            return new Assignment(type, id, expr, end_statement);
        } catch (ParsingError e){
            return null;
        }
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
