package provided.variables;

import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.parameter.Parameter;

import java.util.ArrayList;

public class FunctionCall extends Stmt {
    Token funcName;
    Parameter parameters;

    public FunctionCall(){
        funcName = null;
    }

    public static FunctionCall parseFuncCall(ArrayList<Token> tokens){
        FunctionCall func = new FunctionCall();

        if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
            func.funcName = tokens.get(0);
            tokens.remove(0);
        }

        if (tokens.get(0).getToken().equals("[")) {
            // might need to change this to use the parameters class?
            func.parameters = Parameter.parseParams(tokens);
        }

        return func;
    }

    @Override
    public String convertToJott() {
        return funcName.getToken() + "[" + parameters.convertToJott() + "]";
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
