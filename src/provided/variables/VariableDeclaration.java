package provided.variables;

import provided.Token;
import provided.variables.basics.Type;
import provided.symtable.SymTable;
import java.util.ArrayList;

/**
 * File: VariableDeclaration.java
 * Implementation of VariableDeclaration of the Jott Grammar
 *
 * @author Derek Garcia
 **/

public class VariableDeclaration extends Stmt {

    private final Type type;
    private final String id;
    private final EndStmt end_statement;

    /**
     * Creates new var_dec object
     *
     * @param type variable type
     */
    private VariableDeclaration(Type type, String id, EndStmt end_statement){
        this.type = type;
        this.id = id;
        this.end_statement = end_statement;
    }
    
    /**
     * Parse var_dec
     *
     * @param tokens Tokens to parse
     * @return new var_dec object
     */
    public static VariableDeclaration parseVar_dec(ArrayList<Token> tokens){
        // Get Type
        Type type = Type.parseType(tokens);
        // Get Id
        Token idToken = tokens.remove(0);
        String id = idToken.getToken();
        
        EndStmt end_statement = EndStmt.parseEndStmt(tokens);
        
        SymTable.addVar(id, type.type, idToken.getFilename(), idToken.getLineNum());
        return new VariableDeclaration(type, id, end_statement);
    }

    /**
     * Will output a string of this tree in Jott
     * @return a string representing the Jott code of this tree
     */
    @Override
    public java.lang.String convertToJott() {
        return this.type.convertToJott() + " " + this.id + this.end_statement.convertToJott();
    }

    @Override
    public java.lang.String convertToJava(java.lang.String className) {
        return null;
    }

    @Override
    public java.lang.String convertToC() {
        return this.type.convertToC() + " " + this.id + " = " + this.end_statement.convertToC();
    }

    @Override
    public java.lang.String convertToPython() {
        return this.type.convertToJott() + " " + this.id + this.end_statement.convertToPython();
    }

    @Override
    public boolean validateTree() {
        return true;
    }
}
