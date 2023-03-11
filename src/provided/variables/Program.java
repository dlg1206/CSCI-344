package provided.variables;

import java.util.ArrayList;
import provided.JottTree;
import provided.Token;
import provided.variables.function.FunctionList;

public class Program implements JottTree {


  FunctionList funcList;

  public Program(FunctionList funcList) {
    this.funcList = funcList;
  }


  public static Program parseProgram(ArrayList<Token> tokens) {
    FunctionList funcList = FunctionList.parseFunctionList(tokens);
    return new Program(funcList);
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

