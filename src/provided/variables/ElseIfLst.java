package provided.variables;

import provided.JottTree;
import provided.Token;

import java.util.ArrayList;

public class ElseIfLst implements JottTree {

    ArrayList<ifStmt> ifLst;

    public ElseIfLst(){
        ifLst = null;
    }

    static ElseIfLst ParseElseIfLst(ArrayList<Token> tokens){

        ElseIfLst elifLst = new ElseIfLst();
        boolean moreElIfs = true;
        while (moreElIfs){

            elifLst.ifLst.add(ifStmt.parseIfStmt(tokens));

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
