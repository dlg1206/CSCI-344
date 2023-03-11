package provided.variables;

import provided.*;
import provided.JottTree;
import provided.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BodyStmt implements provided.JottTree {


    //not sure if this is a functional way to deal w/ this and casting is ok
    private final JottTree stmt;

    public BodyStmt(JottTree stmt){
        this.stmt = stmt;
    }

    public static BodyStmt parseBodyStmt(ArrayList<Token> tokens){
        //ArrayList<JottTree> bodyStmts = new ArrayList<JottTree>();

            Token currToken = tokens.get(0);
            //process while token
            if(currToken.getToken().equals("while")){
                return new BodyStmt((JottTree) WhileStmt.parseWhileStmt(tokens));
            }
            else if(currToken.getToken().equals("if")) {
                return new BodyStmt((JottTree) IfStmt.parseIfStmt(tokens));
            }
            else{
                return new BodyStmt((JottTree) Stmt.parseStmt(tokens));
            }

    }

    @Override
    public String convertToJott() {
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
