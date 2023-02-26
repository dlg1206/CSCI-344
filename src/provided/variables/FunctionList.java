package provided.variables;

import java.util.ArrayList;
import provided.JottTree;
import provided.ParsingError;
import provided.Token;

class FunctionList implements JottTree {
  static ArrayList<FunctionDef> funcList = new ArrayList<>();

  public FunctionList() {

  }

  public static FunctionList parseFunctionList(ArrayList<Token> tokens) {
     while (tokens.get(0).getToken().equals("def")) {
			funcList.add(FunctionDef.parseFunctionDef(tokens));
		} 
    if (tokens.size() > 0) {
      // Throw error
      new ParsingError("eof", tokens.get(0).getToken());
      return null;
    }

    return new FunctionList();
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
