package provided.variables.function;

import java.util.ArrayList;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;

public class FunctionList implements JottTree {
  static ArrayList<FunctionDef> funcList = new ArrayList<>();

  public FunctionList() {

  }

  public static FunctionList parseFunctionList(ArrayList<Token> tokens) {
    FunctionDef currFuncDef; 
    while (tokens.get(0).getToken().equals("def")) {
      currFuncDef = FunctionDef.parseFunctionDef(tokens);
			if (currFuncDef == null) {
        return null;
      } 
      funcList.add(currFuncDef);
		} 
    if (tokens.size() > 0) {
      // Throw error
      new ParsingError("Syntax Error", "EOF", tokens.get(0));
      return null;
    }

    return new FunctionList();
  }

  @Override
  public String convertToJott() {
    String result = "";
    for (FunctionDef func : funcList) {
      result += func.convertToJott();
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
