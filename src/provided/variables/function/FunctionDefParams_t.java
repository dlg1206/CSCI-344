package provided.variables.function;

import java.util.ArrayList;
import java.util.HashMap;
import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.basics.Type;

public class FunctionDefParams_t implements JottTree {
  public static HashMap<String, Type> params = new HashMap<>();

  public FunctionDefParams_t() {

  }


  static Token currToken, startToken;
  static String currKey;
  static Type currVal;
  public static FunctionDefParams_t parseFunctionDefParams_t(ArrayList<Token> tokens) {
    startToken = tokens.get(0);
    currToken = startToken;
    while (startToken.getToken().equals(",")) {
      // We have checked for , in previous no need for error throwing
      tokens.remove(0);

      // Check for ID
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
      currToken = tokens.get(0);
      // Check for type:
      Type type = Type.parseType(tokens);
      if (type == null) {
        return null;
      }
      currVal = type;


      params.put(currKey, currVal);

      startToken = tokens.get(0);
      currToken = startToken;
    }

    return new FunctionDefParams_t();
  }

  @Override
  public String convertToJott() {
    String result = "";
    for (String key: params.keySet()) {
      result += ", " + key + ":" + params.get(key) ;
    }
    return result;
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
