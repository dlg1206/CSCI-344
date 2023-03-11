package provided.variables;

import java.util.ArrayList;
import provided.JottTree;
import provided.Token;
import provided.variables.function.FunctionList;

public class Program implements JottTree {


  static FunctionList funcList;

  public Program() {}


  public static Program parseProgram(ArrayList<Token> tokens) {
    funcList = FunctionList.parseFunctionList(tokens);
    if (funcList == null) return null;
    return new Program();
  }

  @Override
  public String convertToJott() {
    return funcList.convertToJott();
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

