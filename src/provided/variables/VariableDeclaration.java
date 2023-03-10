package provided.variables;

import provided.JottTree;
import provided.Token;
import provided.variables.basics.Id;
import provided.variables.basics.Type;

import provided.variables.Stmt;

import java.util.ArrayList;

/**
 * File: VariableDeclaration.java
 * Implementation of VariableDeclaration of the Jott Grammar
 *
 * @author Derek Garcia
 **/

public class VariableDeclaration extends Stmt {

    private final Type type;
     private final Id id;
    // todo add w/ future implementation
    // private final EndStatement end_statement

    /**
     * Creates new var_dec object
     *
     * @param type variable type
     */
    // todo replace with correct
    // private VariableDeclaration(Type type, Id id, EndStatement end_statement)
    private VariableDeclaration(Type type, Id id){
        this.type = type;
         this.id = id;
        // this.end_statement = end_statement;
    }

    /**
     * Parse var_dec
     *
     * @param tokens Tokens to parse
     * @return new var_dec object
     */
    public static VariableDeclaration parseVar_dec(ArrayList<Token> tokens){
         Id id = Id.createId(tokens);
        Type type = Type.getType(tokens.remove(0));
        // EndStatement end_statement = EndStatement.parseEnd_statement(tokens);
        // return new VariableDeclaration(type, id, end_statement);
        return new VariableDeclaration(type, id);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        // return this.type.convertToJott() + " " + this.id.convertToJott() + this.end_statement.convertToJott();
        return null;
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
