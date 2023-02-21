implements FunctionDef extends JottTree {
  
  static public ArrayList<FunctionDefParams> paramsList;
  
  static public parseFunctionDef(ArrayList<Token> tokens) {
    // Check def
    Token currToken = tokens.get(0);
    if (!currToken.getToken().equals("def")) {
      // Throw error
      System.err.println("");
      return null;
    } 
    tokens.remove(0);
    // Check <id>
    currToken = tokens.get(0);
    if (currToken.getType() != TokenType.ID_KEYWORD) {
      return null;     
    }
    tokens.remove(0);
    // Check [
    currToken = tokens.get(0);
    if (!currToken.getToken().equals("[")) {
      return null;     
    }
    tokens.remove(0);
    // Check Function_def_params
    paramsList = parseFunctionDefParams(tokens);
    // Check ]
    currToken = tokens.get(0);
    if (!currToken.getToken().equals("[")) {
      return null;     
    }
    tokens.remove(0);
    // Check :
    currToken = tokens.get(0);
    if (!currToken.getToken().equals(":")) {
      return null;     
    }
    tokens.remove(0);
    // Check FunctionReturn
    parseFunctionReturn();
    // Check {
    currToken = tokens.get(0);
    if (!currToken.getToken().equals("{")) {
      return null;     
    }
    tokens.remove(0);
    // Check Body
    bodyList = parseBody(tokens);
    // Check }
    currToken = tokens.get(0);
    if (!currToken.getToken().equals("}")) {
      return null;     
    }
    tokens.remove(0);
  {

}
