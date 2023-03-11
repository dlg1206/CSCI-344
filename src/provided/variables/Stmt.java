package provided.variables;

import provided.*;
import provided.variables.basics.Type;

import java.util.ArrayList;
import java.util.Stack;

public class Stmt implements JottTree {

    EndStmt endStmt;
    Stmt stmt;

    public Stmt() {}

    public Stmt(Stmt stmt, EndStmt endStmt){
        this.stmt = stmt;
        this.endStmt = endStmt;
    }

    static Stmt parseStmt(ArrayList<Token> tokens){
        
        if (Type.isType(tokens.get(0))){
            if (tokens.get(2).getToken().equals("=")){
                Stmt statement = Assignment.parseAsmt(tokens);
                return new Stmt(statement, null);
            } else {
                Stmt statement = VariableDeclaration.parseVar_dec(tokens);
                return new Stmt(statement, null);
            }
        } else if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
            if (tokens.get(1).getToken().equals("=")){
                Stmt statement = Assignment.parseAsmt(tokens);
                return new Stmt(statement, null);
            } else {
                
                Stmt statement = FunctionCall.parseFuncCall(tokens);
                EndStmt endStmt = EndStmt.parseEndStmt(tokens);
                return new Stmt(statement, endStmt);
            }
        }
        else {
            throw new ParsingError("Syntax Error", "Assignment, Variable Declaration, or Function Call", tokens.get(0));
        }
    }

    @Override
    public String convertToJott() {
        if (endStmt != null) return stmt.convertToJott() + endStmt.convertToJott(); 
        return stmt.convertToJott();
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
