package provided.variables;

import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.symtable.Function;
import provided.symtable.SymTable;
import provided.variables.parameter.Parameter;

import java.util.ArrayList;
import java.util.Locale;

public class FunctionCall extends Stmt {
    String id;
    Parameter params;
    String functionCalling;

    public FunctionCall(String id, Parameter params, String functionCalling) {
        this.id = id;
        this.params = params;
        this.functionCalling = functionCalling;
    }

    public static Token currToken;
    public static FunctionCall parseFuncCall(ArrayList<Token> tokens, String functionCalling){
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
            parameters = Parameter.parseParams(tokens, functionCalling);
        }

        if (!tokens.get(0).getToken().equals("]")) {
            throw new ParsingError("Syntax Error", "1]", tokens.get(0));
        }

        tokens.remove(0);
        return new FunctionCall(id, parameters, functionCalling);
    }

    @Override
    public String convertToJott() {
        if (params != null) return id + "[" + params.convertToJott() + "]";
        return id + "[]";
    }

    @Override
    public String convertToJava(String className) {

        String returnString = "";
        if (this.functionCalling.equals("main")){
            returnString = returnString + className.toUpperCase(Locale.ROOT) + ".";
        }
        if (id.equals("print")){
            if (params != null) return "System.out.println( " + params.convertToJava(className) + " )";
            return "System.out.println()";
        }
        if (id.equals("concat")){
            return params.getFirstParameter().convertToJava(className) + ".concat(" + params.getParams_t().convertToJava(className).substring(1) + ")";
        }
        if (id.equals("length")){
            return params.convertToJava(className) + ".length()";
        }
        if (params != null) return returnString + id + "( " + params.convertToJava(className) + " )";

        return returnString + id + "()";
    }

    @Override
    public String convertToC() {
        if(this.id.equals("concat")){
            //concat -> strcat
            return "strcat(" + params.convertToC() + ")";
        }
        if(this.id.equals("print")){
            if(params != null){
                    System.out.println(params.convertToJott());
                //printf + type 
                String paramType = params.getTypes().get(0);
                if(paramType.equals("Boolean")){
                    paramType = "%i";
                }
                else if(paramType.equals("String")){
                    paramType = "%s";
                }
                else if(paramType.equals("Double")){
                    paramType = "%d";
                }
                else if(paramType.equals("Integer")){
                    paramType = "%s";
                }
                return "printf(\"" + paramType + "\", " + params.convertToC() + ")";
            }
            return "printf()";
        }
        if(this.id.equals("length")){
            //strlen
        }
        return this.id + "( " + (params != null ? params.convertToC() : "") + " )";
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
