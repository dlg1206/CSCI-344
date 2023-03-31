package provided.variables;

import provided.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EndStmt implements JottTree{

    public EndStmt(){}
    static Token currToken;
    public static EndStmt parseEndStmt(ArrayList<Token> tokens){
        currToken = tokens.get(0); 
        if(currToken.getToken().equals(";")){
            tokens.remove(0);
            return new EndStmt();
        }
        throw new ParsingError("Syntax Error", ";", tokens.get(0));
    }

    @Override
    public String convertToJott() {
        return ";";
    }

    @Override
    public String convertToJava(String className) {
        return ";";
    }

    @Override
    public String convertToC() {
        return ";";
    }

    @Override
    public String convertToPython() {
        return "";
    }

    @Override
    public boolean validateTree() {
        return true;
    }

}
