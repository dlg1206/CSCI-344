package provided.variables;

import provided.JottTree;
import provided.Token;
import provided.variables.expr.BoolExp;

import java.util.ArrayList;

public class ElseIfLst implements JottTree {

    ArrayList<IfStmt> ifLst;
    BoolExp boolExp;
    Body body;

    public ElseIfLst(){
        ifLst = null;
    }

    static ElseIfLst ParseElseIfLst(ArrayList<Token> tokens){

        ElseIfLst elifLst = new ElseIfLst();
        boolean moreElIfs = true;
        while (moreElIfs){
            
            elifLst.ifLst.add(IfStmt.parseIfStmt(tokens));

            if (tokens.get(0).getToken().equals("elseif")){
                tokens.remove(0);
            }
            else {
                moreElIfs = false;
            }
        }
        return new ElseIfLst(); // return the ElseIfLst as an object list of if statements
    }

    @Override
    public String convertToJott() {
        String converted = "";
        for (IfStmt ifstmt : ifLst){
            converted = converted + "else" + ifstmt.convertToJott();
        }
        return converted;
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
        if(!boolExp.validateTree()){return false;}
        if(!body.validateTree()){return false;}
        for(IfStmt i : ifLst){
            if(i.validateTree() == false){
                return false;
            }
        }
        return true;
    }
}
