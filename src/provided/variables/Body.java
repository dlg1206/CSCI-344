package provided.variables;

import provided.JottTree;
import provided.*;

import java.util.ArrayList;

public class Body implements JottTree{

    BodyStmt bodyStmt;
    Body nextBody;
    ReturnStmt returnStmt;

    public Body(BodyStmt bodyStmt, Body nexBody, ReturnStmt returnStmt){
        this.bodyStmt = bodyStmt;
        this.nextBody = nexBody;
        this.returnStmt = returnStmt;
    }

    public static Body parseBody(ArrayList<Token> tokens){
        if(tokens.get(0).getToken().equals("}")){
            return new Body(null, null, null);
        }
        if(tokens.get(0).getToken().equals("return")){
            return new Body(null, null, ReturnStmt.parseReturnStmt(tokens));
        }
        else{
            return new Body(BodyStmt.parseBodyStmt(tokens), Body.parseBody(tokens), null);
        }

    }

    @Override
    public String convertToJott() {
        if(bodyStmt == null && nextBody == null && returnStmt == null){
            return "";
        }
        else if(returnStmt != null){
            return returnStmt.convertToJott();
        }
        else{
            return bodyStmt.convertToJott() + nextBody.convertToJott();
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
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
