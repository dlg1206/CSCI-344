package provided.variables;

import provided.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EndStmt extends JottTree{

    public EndStmt(){

    }

    public ArrayList<Token> parseEndStmt(ArrayList<Token> tokens){

        tokens.remove(0);
        return tokens;
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
