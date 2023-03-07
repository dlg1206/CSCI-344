package provided.variables;

import provided.*;
import provided.JottTree;
import provided.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BodyStmt implements provided.JottTree {

    public BodyStmt(){

    }

    public ArrayList<JottTree> parseBodyStmt(ArrayList<Token> tokens){
        ArrayList<JottTree> bodyStmts = new ArrayList<JottTree>();
        while(tokens.size() != 0){
            Token currToken = tokens.get(0);
            //process while token
            if(currToken.getToken().equals("while") | currToken.getToken().equals("While")){
                bodyStmts.add(whileLoop.parseWhileLoop(tokens));
            }
            else if(currToken.getToken().equals("if") | currToken.getToken().equals("If")){
                bodyStmts.add(ifStmt.parseIfStmt(tokens));
            }
            else{
                bodyStmts.add(Stmt.parseIfStmt(tokens));
            }
        }
        return bodyStmts;
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
