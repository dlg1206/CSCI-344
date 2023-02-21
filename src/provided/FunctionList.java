interface FunctionList extends JottTree {
  static public parseFunctionList(ArrayList<Token> tokens) {
     while (tokens.get(0).getToken().equals('def')) {
			return parseFunctionDef();	
		} 
    if (tokens.size() != 0) {
      // Throw error 
    }
  }
}
