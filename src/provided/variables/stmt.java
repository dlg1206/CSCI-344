package provided.variables;

import provided.*;
import provided.variables.ifStmt;

import java.util.ArrayList;

public class Stmt implements JottTree {

    public Stmt(){

    }

    static Stmt parseStmt(ArrayList<Token> tokens){
        Stmt statement = new Stmt();

        if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
            if (tokens.get(1).getToken().equals("=")){
                statement = Assignment.parseAsmt(tokens);
            }
            statement = funcCall.parseFuncCall(tokens);
        }

        else if (tokens.get(0).getToken().equals("String") ||tokens.get(0).getToken().equals("Boolean") || tokens.get(0).getToken().equals("Integer") ||tokens.get(0).getToken().equals("Double")){
            if (tokens.get(2).getToken().equals("=")){
                statement = Assignment.parseAsmt(tokens);
            }
            else {
                statement = VariableDeclaration.parseVar_dec(tokens);
            }
        }

        else {
            // error
        }

        return statement;
    }




    @Override
    public String convertToJott() {
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
