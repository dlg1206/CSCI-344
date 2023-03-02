package provided.variables;

import java.util.ArrayList;
import provided.Bodystmt;
import provided.FunctionReturn;
import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.TokenType;

class FunctionDef extends JottTree {
  static public FunctionDefParams params;
  static public Body body;
  static public FunctionReturn returnRef;

  public FunctionDef() {

  }

  public static FunctionDef parseFunctionDef(ArrayList<Token> tokens){
    // Check def
    Token currToken=tokens.get(0);
    if(!currToken.getToken().equals("def")) {
      // Throw error
      new ParsingError("Syntax Error", "def", currToken.getToken());
      return null;
    }
    tokens.remove(0);
    // Check <id>
    currToken=tokens.get(0);
    if(currToken.getTokenType()!= TokenType.ID_KEYWORD){
      new ParsingError("Syntax Error", "Id or Keyword", currToken.getToken());
      return null;
    }
    tokens.remove(0);
    // Check [
    currToken=tokens.get(0);
    if(!currToken.getToken().equals("[")){
      new ParsingError("Syntax Error", "[", currToken.getToken());
      return null;
    }
    tokens.remove(0);
    // Check Function_def_params
    params = FunctionDefParams.parseFunctionDefParams(tokens);
    if (params == null) {
      return null;
    }
    // Check ]
    currToken=tokens.get(0);
    if(!currToken.getToken().equals("]")){
      new ParsingError("Syntax Error", "]", currToken.getToken());
      return null;
    }
    tokens.remove(0);
    // Check :
    currToken=tokens.get(0);
    if(!currToken.getToken().equals(":")){
      new ParsingError("Syntax Error", ":", currToken.getToken());
      return null;
    }
    tokens.remove(0);
    // Check FunctionReturn
    returnRef = FunctionReturn.parseFunctionReturn();
    if (returnRef == null) {
      return null;
    }
    // Check {
    currToken=tokens.get(0);
    if(!currToken.getToken().equals("{")){
      new ParsingError("Syntax Error", "{", currToken.getToken());
      return null;
    }
    tokens.remove(0);
    // Check Body
    body = Body.parseBody(tokens);
    if (body == null) {
      return null;
    }
    // Check }
    currToken=tokens.get(0);
    if(!currToken.getToken().equals("}")){
      new ParsingError("Syntax Error", "}", currToken.getToken());
      return null;
    }
    tokens.remove(0);

    return new FunctionDef();
  }
}
