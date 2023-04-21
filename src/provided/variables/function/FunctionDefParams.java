package provided.variables.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.basics.Type;

public class FunctionDefParams implements JottTree {


  FunctionDefParams_t functionDefs_t;
  String firstId;
  Type firstType;

  public FunctionDefParams(FunctionDefParams_t functionDefs_t, String firstId, Type firstType) {
    this.functionDefs_t = functionDefs_t;
    this.firstId = firstId;
    this.firstType = firstType;
  }

  public ArrayList<String> getParamsList() {
    ArrayList<String> paramsTypes;
    if (this.functionDefs_t != null) {
      paramsTypes = this.functionDefs_t.getParamsList();
    } else {
      paramsTypes = new ArrayList<>();
    }
    paramsTypes.add(this.firstType.type);

    return paramsTypes;
  }


  static Token currToken;
  public static FunctionDefParams parseFunctionDefParams(ArrayList<Token> tokens) {
    HashMap<String, Type> params = new HashMap<String, Type>();

    // Check for Keyword
    currToken=tokens.get(0);
    if(currToken.getTokenType() != TokenType.ID_KEYWORD)
      throw new ParsingError("Syntax Error", "Id or Keyword", currToken);
    String id = currToken.getToken();
    tokens.remove(0);
    // Check for :
    currToken=tokens.get(0);
    if (!currToken.getToken().equals(":"))
      throw new ParsingError("Syntax Error", ":", currToken);
    tokens.remove(0);
    // Check for type
    currToken = tokens.get(0);
    Type type = Type.parseType(tokens);
    if (type == null) {
      throw new ParsingError("Syntax Error", "Type", currToken);
    }
    currToken = tokens.get(0);
    

    FunctionDefParams_t funcParams_t = null;
    if (currToken.getToken().equals(",")) {
      funcParams_t = FunctionDefParams_t.parseFunctionDefParams_t(tokens);
    }
    

    return new FunctionDefParams(funcParams_t, id, type);
  }


  @Override
  public String convertToJott() {
    if (functionDefs_t != null) return firstId + ":" + firstType.convertToJott() + functionDefs_t.convertToJott();
    return firstId + ":" + firstType.convertToJott();  
  }

  @Override
  public String convertToJava(String className) {
    if (functionDefs_t != null) return firstType.convertToJava(className) + " " +  firstId + functionDefs_t.convertToJava(className);
    return firstType.convertToJava(className) + " " + firstId;
  }

  @Override
  public String convertToC() {
    return null;
  }

  @Override
  public String convertToPython() {
    if (functionDefs_t != null) return firstId + functionDefs_t.convertToPython();
    return firstId;
  }

  @Override
  public boolean validateTree() {
    // check tail if not null
    if(this.functionDefs_t != null)
      return functionDefs_t.validateTree() && firstType.validateTree();
    return firstType.validateTree();
  }
}
