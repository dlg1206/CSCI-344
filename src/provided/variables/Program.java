package provided.variables;

import java.util.ArrayList;
import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.symtable.SymTable;
import provided.variables.function.FunctionList;

public class Program implements JottTree {


  FunctionList funcList;
  static String fileName;

  public Program(FunctionList funcList) {
    this.funcList = funcList;
  }


  public static Program parseProgram(ArrayList<Token> tokens) {
    fileName = tokens.get(0).getFilename();
    SymTable.getNewSymTable();
    FunctionList funcList = FunctionList.parseFunctionList(tokens);
    //System.out.println(SymTable.staticToString());
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
    if (SymTable.getFunction("main") == null) {
      new ParsingError("Semantic Error", "no main function.", fileName, 0);
      return false;
    }
    return funcList.validateTree();
  }
}

