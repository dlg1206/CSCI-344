package provided.variables.function;

import java.util.ArrayList;
import provided.variables.BodyStmt;
import provided.variables.basics.Id;
import provided.variables.function.FunctionReturn;
import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.Body;

class FunctionDef implements JottTree {

  //missing the func name id? 
  static public FunctionDefParams params;
  static public Body body;
  static public FunctionReturn returnRef;
  static private String funcId;
  public FunctionDef() {

  }

  public static FunctionDef parseFunctionDef(ArrayList<Token> tokens){
    // Check def
    Token currToken=tokens.get(0);
    if(!currToken.getToken().equals("def")) {      
      throw new ParsingError("Syntax Error", "def", currToken);
    }
    tokens.remove(0);
    // Check <id>
    currToken=tokens.get(0);
    if(currToken.getTokenType()!= TokenType.ID_KEYWORD){
      throw new ParsingError("Syntax Error", "Id or Keyword", currToken);
    }
    funcId = currToken.getToken();
    tokens.remove(0);
    // Check [
    currToken=tokens.get(0);
    if(!currToken.getToken().equals("[")){
      throw new ParsingError("Syntax Error", "[", currToken);
    }
    tokens.remove(0);
    // Check Function_def_params
    currToken=tokens.get(0);
    if (!currToken.getToken().equals("]"))  {
      params = FunctionDefParams.parseFunctionDefParams(tokens);
    }
    // Check ]
    if(!currToken.getToken().equals("]")){
      throw new ParsingError("Syntax Error", "]", currToken);
    }
    tokens.remove(0);
    // Check :
    currToken=tokens.get(0);
    if(!currToken.getToken().equals(":")){
      throw new ParsingError("Syntax Error", ":", currToken);
    }
    tokens.remove(0);
    // Check FunctionReturn
    returnRef = FunctionReturn.parseFunctionReturn(tokens); // I changed this, not sure if it's correct - Josh
    if (returnRef == null) {
      return null;
    }
    // Check {
    currToken = tokens.get(0);
    if(!currToken.getToken().equals("{")){
      throw new ParsingError("Syntax Error", "{", currToken);
    }
    tokens.remove(0);
    // Check Body
    body = Body.parseBody(tokens);
    if (body == null) {
      return null;
    }
    // Check }
    currToken = tokens.get(0);
    if(!currToken.getToken().equals("}")){
      throw new ParsingError("Syntax Error", "}", currToken);
    }
    tokens.remove(0);

    return new FunctionDef();
  }

  @Override
  public String convertToJott() {
    if (params != null) {
      return "def " + funcId + "[" + params.convertToJott() + "]:" + returnRef.convertToJott() + "{" + body.convertToJott() + "}";
    } else {
      return "def " + funcId + "[]:" + returnRef.convertToJott() + "{" + body.convertToJott() + "}";
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
