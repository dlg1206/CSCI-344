package provided.variables.function;

import java.util.ArrayList;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.Body;
import provided.variables.ReturnStmt;

class FunctionDef implements JottTree {

  //missing the func name id? 
  FunctionDefParams defParams;
  Body body;
  FunctionReturn funcReturn;
  String funcId;

  public FunctionDef(FunctionDefParams defParams, Body body, FunctionReturn funcReturn, String funcId) {
    this.defParams = defParams;
    this.body = body;
    this.funcReturn = funcReturn;
    this.funcId = funcId;
    
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
    String funcId = currToken.getToken();
    tokens.remove(0);
    // Check [
    currToken=tokens.get(0);
    if(!currToken.getToken().equals("[")){
      throw new ParsingError("Syntax Error", "[", currToken);
    }
    tokens.remove(0);
    // Check Function_def_params
    currToken=tokens.get(0);
    FunctionDefParams params = null;
    
    
    
    if (!currToken.getToken().equals("]"))  {
      params = FunctionDefParams.parseFunctionDefParams(tokens);
      
    }
    currToken = tokens.get(0);
    // Check ]
    currToken = tokens.get(0);
    if(!currToken.getToken().equals("]")){
      throw new ParsingError("Syntax Error", "4]", currToken);
    }
    tokens.remove(0);
    // Check :
    currToken=tokens.get(0);
    if(!currToken.getToken().equals(":")){
      throw new ParsingError("Syntax Error", ":", currToken);
    }
    tokens.remove(0);
    
    currToken = tokens.get(0);
    // Check FunctionReturn
    FunctionReturn returnRef = FunctionReturn.parseFunctionReturn(tokens); // I changed this, not sure if it's correct - Josh
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
    Body body = Body.parseBody(tokens);
    // Check }
    currToken = tokens.get(0);
    
    if(!currToken.getToken().equals("}")){
      throw new ParsingError("Syntax Error", "}", currToken);
    }
    tokens.remove(0);

    return new FunctionDef(params, body, returnRef, funcId);
  }

  @Override
  public String convertToJott() {
    if (defParams != null) {
      return "def " + funcId + "[" + defParams.convertToJott() + "]:" + funcReturn.convertToJott() + "{\n" + body.convertToJott() + "}\n\n";
    } else {
      return "def " + funcId + "[]:" + funcReturn.convertToJott() + "{\n" + body.convertToJott() + "}\n\n";
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
