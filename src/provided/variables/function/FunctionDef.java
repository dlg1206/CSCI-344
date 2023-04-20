package provided.variables.function;

import java.util.ArrayList;
import provided.symtable.SymTable;
import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.Body;
import provided.variables.ReturnStmt;
import provided.variables.basics.Type;

public class FunctionDef implements JottTree {

  //missing the func name id? 
  FunctionDefParams defParams;
  Body body;
  FunctionReturn funcReturn;
  String funcId;

  public FunctionDef(FunctionDefParams defParams, Body body, FunctionReturn funcReturn, String funcId) {
    this.defParams = defParams;
    this.body = body;
    body.isRoot = true;
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
    Token idToken = currToken;
    if(currToken.getTokenType()!= TokenType.ID_KEYWORD){
      throw new ParsingError("Syntax Error", "Id or Keyword", currToken);
    }
    String funcId = currToken.getToken();
    SymTable.updateScope(funcId);
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
    Body body = Body.parseBody(tokens, 0);
    body.isRoot = true;
    // Check }
    currToken = tokens.get(0);
    
    if(!currToken.getToken().equals("}")){
      throw new ParsingError("Syntax Error", "}", currToken);
    }
    tokens.remove(0);
    ArrayList<String> paramsList = params != null ? params.getParamsList() : null;
    String returnType = returnRef.returnType;    
    SymTable.removeScopeLayer();
    SymTable.addFunc(funcId, paramsList, returnType, idToken.getFilename(), idToken.getLineNum());

    body.isRoot = true;
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
    if (funcId.equals("main")){
      if (defParams != null) {
        return "public static" + funcReturn.convertToJava(className) + " " + funcId + "(String args[], " + defParams.convertToJava(className) + "){" + body.convertToJava(className) + "}";
      } else {
        return "public static" + funcReturn.convertToJava(className) + " " + funcId + "(String args[]){" + body.convertToJava(className) + "}";
      }
    }
    if (defParams != null) {
      return "public " + funcReturn.convertToJava(className) + " " + funcId + "(" + defParams.convertToJava(className) + "){" + body.convertToJava(className) + "}";
    } else {
      return "public " + funcReturn.convertToJava(className) + " " + funcId + "(){" + body.convertToJava(className) + "}";
    }
  }

  @Override
  public String convertToC() {
    return null;
  }

  @Override
  public String convertToPython() {
    return (defParams != null)
      ? "def " + funcId + "(" + defParams.convertToPython() + "):" + "\n" + body.convertToPython() + "\n\n"
      : "def " + funcId + "():\n" + body.convertToPython() + "\n\n";
  }

  @Override
  public boolean validateTree() {
    SymTable.updateScope(this.funcId);
    if(body.validateTree() && funcReturn.validateTree()){
      //check the return type matches the func return type
      Type bodyReturnType = body.isReturnable();

      // todo function can return nothing?
//      if(bodyReturnType == null){
//        //System.out.println("body return error1");
//        return false;
//      }
      Type funcReturnType = new Type(funcReturn.returnType);
      if(bodyReturnType != null && !bodyReturnType.equals(funcReturnType)){
        //System.out.println("body return error2");
        return false;
      }

      if(defParams != null) {
        SymTable.removeScopeLayer();
        return defParams.validateTree();
      }
      
      SymTable.removeScopeLayer();
      return true;
    }
    SymTable.removeScopeLayer();
    return false;
  }
}
