package provided.variables;

import provided.JottTree;
import provided.*;

import java.util.ArrayList;

public class Body implements JottTree{

    public Body(){

    }

    public Body parseBody(ArrayList<Token> tokens){
        
        while(true){
            if(tokens.size() == 0){
                return new Body();
            }
            if(tokens.get(0).getToken().equals("return")){
                
            }
        }

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
