package provided;

import java.util.ArrayList;

public class funcCall implements JottTree {

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

        if (tokens.get(0).toString().equals("(")) {
            tokens.remove(0);
            while (!tokens.get(0).toString().equals(")")) {
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
