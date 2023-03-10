package provided.variables.function;

import java.util.ArrayList;
import java.util.HashMap;
import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.Type;
import provided.variables.basics.Type;

public class FunctionDefParams implements JottTree {

  static HashMap<String, Type> params;

  static FunctionDefParams_t functionDefs_t;
  public FunctionDefParams() {

  }


  static Token currToken;
  static String currKey;
  static Type currVal;
  public static FunctionDefParams parseFunctionDefParams(ArrayList<Token> tokens) {
    // Check for Keyword
    currToken=tokens.get(0);
    if(currToken.getTokenType()!= TokenType.ID_KEYWORD){
      new ParsingError("Syntax Error", "Id or Keyword", currToken);
      return null;
    }
    currKey = currToken.getToken();
    tokens.remove(0);
    // Check for :
    currToken=tokens.get(0);
    if (!currToken.getToken().equals(":")) {
      new ParsingError("Syntax Error", ":", currToken);
      return null;
    }
    tokens.remove(0);

    // Check for type:
    Type type = Type.parseType(tokens);
    if (type == null) {
      return null;
    }
    currVal = type;

    currToken = tokens.get(0);
    if (currToken.getToken().equals(",")) {
      functionDefs_t = FunctionDefParams_t.parseFunctionDefParams_t(tokens);
      if (functionDefs_t == null) {
        params = new HashMap<String, Type>();
      }
    }
    if (params == null) {
      params = new HashMap<String, Type>();
    }
    params.put(currKey, currVal);

    return new FunctionDefParams();
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
