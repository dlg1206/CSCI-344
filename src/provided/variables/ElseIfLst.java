package provided;

import java.util.ArrayList;

public class ElseIfLst implements JottTree {

    public ElseIfLst(){

    }

    static ElseIfLst ParseElseIfLst(ArrayList<Token> tokens){
        boolean moreElIfs = true;
        while (moreElIfs){

            if (tokens.get(0).toString().equals("(")){
               tokens.remove(0);
               // parse the boolean list
            }

            if (tokens.get(0).toString().equals("{")){
                tokens.remove(0);
                //parse the body
            }
            if (tokens.get(0).toString().equals("elif")){
                tokens.remove(0);
                // could perhaps remove while loop and instead recursively call this method,
                // loop should replicate the recursion though
            }
            else {
                moreElIfs = false;
            }
        }
        return new ElseIfLst(); // return the ElseIfLst as an object list of bodies (?)
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
