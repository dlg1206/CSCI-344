package provided.variables;

import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.symtable.Function;
import provided.symtable.SymTable;
import provided.variables.parameter.Parameter;

import java.util.ArrayList;

public class FunctionCall extends Stmt {
    String id;
    Parameter params;

    public FunctionCall(String id, Parameter params) {
        this.id = id;
        this.params = params;
    }

    public static Token currToken;
    public static FunctionCall parseFuncCall(ArrayList<Token> tokens){
        currToken = tokens.get(0); 
        if (currToken.getTokenType() != TokenType.ID_KEYWORD){
            throw new ParsingError("Syntax Error", "Id or Keyword", currToken);
        }
        String id = currToken.getToken();
        tokens.remove(0);
        currToken = tokens.get(0);
        if (!tokens.get(0).getToken().equals("[")) {
            throw new ParsingError("Syntax Error", "[", currToken);
        }
        tokens.remove(0);
        
        // might need to change this to use the parameters class?
        Parameter parameters = null;
        if (!tokens.get(0).getToken().equals("]")) {
            parameters = Parameter.parseParams(tokens);
        }

        if (!tokens.get(0).getToken().equals("]")) {
            throw new ParsingError("Syntax Error", "1]", tokens.get(0));
        }

        tokens.remove(0);
        return new FunctionCall(id, parameters);
    }

    @Override
    public String convertToJott() {
        if (params != null) return id + "[" + params.convertToJott() + "]";
        return id + "[]";
    }

    @Override
    public String convertToJava(String className) {

        if (id.equals("print")){
            id = "System.out.println()";
        }
        if (params != null) return id + "( " + params.convertToJott() + " )";
        return id + "()";
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        if (params != null) return id + "(" + params.convertToPython() + ")";
        return id + "()";
    }

    @Override
    public boolean validateTree() {

       Function func = SymTable.getFunction(this.id);
       // check if function exists or if it's a print keyword
       if (func == null && !this.id.equals("print")){
           return false;
       }

       if (params != null && !params.validateTree()){
           return false;
       }
// todo this might break stuff?
//       ArrayList<String> thisFuncParams = params.getTypes();
//       ArrayList<String> funcDefParams = func.getParamsType();
//
//       return thisFuncParams.equals(funcDefParams);
        return true;
    }
}
