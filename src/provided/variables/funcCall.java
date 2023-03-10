package provided.variables;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class funcCall extends Stmt {
    //TODO Need to change ID to the id class
    Token funcName;
    ArrayList<Token> parameters;

    public funcCall(){
        funcName = null;
        parameters = new ArrayList<Token>();
    }

    static funcCall parseFuncCall(ArrayList<Token>tokens){
        funcCall func = new funcCall();

        if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
            func.funcName = tokens.get(0);
            tokens.remove(0);
        }

        if (tokens.get(0).getToken().equals("(")) {
            tokens.remove(0);
            // might need to change this to use the parameters class?
            while (!tokens.get(0).getToken().equals(")")) {
                func.parameters.add(tokens.get(0));
                tokens.remove(0);
            }
        }

        return null;
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
