package provided.variables.function;

import java.util.ArrayList;
import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.basics.Type;

public class FunctionDefParams_t implements JottTree {
  ArrayList<String> ids;
  ArrayList<String> types;
  public FunctionDefParams_t(ArrayList<String> ids, ArrayList<String> types) {
    this.ids = ids;
    this.types = types;
  }

  public ArrayList<String> getParamsList() {
    return this.types;
  }

  static Token currToken;
  static String currKey;
  static Type currVal;
  public static FunctionDefParams_t parseFunctionDefParams_t(ArrayList<Token> tokens) {
    currToken = tokens.get(0);
    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> types = new ArrayList<>();
    while (currToken.getToken().equals(",")) {
      // We have checked for , in previous no need for error throwing
      tokens.remove(0);

      // Check for ID
      currToken=tokens.get(0);
      if(currToken.getTokenType()!= TokenType.ID_KEYWORD){
        throw new ParsingError("Syntax Error", "Id or Keyword", currToken);
      }
      String id = currToken.getToken();
      tokens.remove(0);
      // Check for :
      currToken=tokens.get(0);
      if (!currToken.getToken().equals(":")) {
        throw new ParsingError("Syntax Error", ":", currToken);
      }
      tokens.remove(0);
      currToken = tokens.get(0);
      // Check for type:
      Type type = Type.parseType(tokens);
      if (type == null) {
        return null;
      }
      
      ids.add(id);
      types.add(type.type);
    }

    return new FunctionDefParams_t(ids, types);
  }

  @Override
  public String convertToJott() {
    String result = "";
    for (int i=0; i<ids.size();i++) {
      result += ", " + ids.get(i) + ":" + types.get(i);
    }
    return result;
  }

  @Override
  public String convertToJava(String className) {
    return null;
  }

  @Override
  public String convertToC() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < this.ids.size(); i++) {
      result.append(", ").append(this.types.get(i)).append(" ").append(this.ids.get(i));
    }
    return result.toString();
  }

  @Override
  public String convertToPython() {
    String result = "";
    for (int i=0; i<ids.size();i++) {
      result += ", " + ids.get(i);
    }
    return result;
  }

  @Override
  public boolean validateTree() {
      return true;
  }
}
