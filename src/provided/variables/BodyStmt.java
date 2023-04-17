package provided.variables;

import provided.*;
import provided.JottTree;
import provided.Token;

import java.lang.reflect.Array;
import java.sql.Struct;
import java.util.ArrayList;

public class BodyStmt implements provided.JottTree {


    //not sure if this is a functional way to deal w/ this and casting is ok
    public IfStmt ifStmt;
    private WhileStmt whileStmt;
    private Stmt stmt; 
    public enum StmtType{
        WHILE, 
        IF,
        OTHER
    }
    public StmtType type;

    public BodyStmt(IfStmt ifStmt, WhileStmt whileStmt, Stmt stmt){
        this.ifStmt = ifStmt;
        this.whileStmt = whileStmt;
        this.stmt = stmt;
    }

    public static BodyStmt parseBodyStmt(ArrayList<Token> tokens){
        //ArrayList<JottTree> bodyStmts = new ArrayList<JottTree>();
            
            Token currToken = tokens.get(0);
            //process while token
            
            if(currToken.getToken().equals("while")){
                BodyStmt bStmt = new BodyStmt(null, WhileStmt.parseWhileStmt(tokens), null);
                bStmt.type = StmtType.WHILE;
                return bStmt;
            }
            else if(currToken.getToken().equals("if")) {
                BodyStmt bStmt = new BodyStmt(IfStmt.parseIfStmt(tokens), null, null);
                bStmt.type = StmtType.IF;
                return bStmt;
            }
            else {
                BodyStmt bStmt = new BodyStmt(null, null, Stmt.parseStmt(tokens));
                bStmt.type = StmtType.OTHER;
                return bStmt;
            } 

    }

    @Override
    public String convertToJott() {
        if(whileStmt != null){
            return whileStmt.convertToJott();
        }
        else if(ifStmt != null){
            return ifStmt.convertToJott();
        }
        else if(stmt != null){
            return stmt.convertToJott();
        }
        else{
            return null;
        } 
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
        if(whileStmt != null){
            return whileStmt.convertToPython();
        }
        else if(ifStmt != null){
            return ifStmt.convertToPython();
        }
        else if(stmt != null){
            return stmt.convertToPython();
        }
        else{
            return null;
        } 
    }

    @Override
    public boolean validateTree() {
        if(whileStmt != null){
            return whileStmt.validateTree();
        }
        else if(ifStmt != null){
            return ifStmt.validateTree();
        }
        else if(stmt != null){
            return stmt.validateTree();
        }
        else{
            return false;
        }
    }
}
