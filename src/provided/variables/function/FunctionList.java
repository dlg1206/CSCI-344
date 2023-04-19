package provided.variables.function;

import java.util.ArrayList;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.symtable.SymTable;

public class FunctionList implements JottTree {
  ArrayList<FunctionDef> funcList;

  public FunctionList(ArrayList<FunctionDef> funcList) {
    this.funcList = funcList;
  }

  public static FunctionList parseFunctionList(ArrayList<Token> tokens) {
    FunctionDef currFuncDef; 
    ArrayList<FunctionDef> funcList = new ArrayList<>();
    while (tokens.size() > 0 && tokens.get(0).getToken().equals("def")) {
      funcList.add(FunctionDef.parseFunctionDef(tokens));
		} 
    if (tokens.size() > 0)
      throw new ParsingError("Syntax Error", "EOF", tokens.get(0));



    return new FunctionList(funcList);
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
    String result = "";
    for (FunctionDef func : funcList) {
      result += func.convertToPython();
    }
    return result;
  }

  @Override
  public boolean validateTree() {
    if (SymTable.getFunction("main") == null){
      return false;
    }
    for (FunctionDef func : funcList) {
      if (!func.validateTree()){
        return false;
      }
    }
    return true;
  }
}
