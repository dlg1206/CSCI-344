package provided.variables.expr;

import provided.JottTree;
import provided.Token;
import provided.TokenType;
import java.util.ArrayList;
import provided.ParsingError;

public class BoolExp implements JottTree {
    
    static boolean IS_ID = false;
    static boolean IS_BOOL = false;
    static boolean IS_NEXPR = false;
    static boolean IS_FUNCCALL = false;
    

    static String id;
    public BoolExp() {}

    static Token currToken; 

    static public BoolExp parseBool (ArrayList<Token> tokens) {

        currToken = tokens.get(0);
        if (currToken.getTokenType() == TokenType.ID_KEYWORD) {
            // ID or N_EXP or Func Call
            id = currToken.getToken();

        
        } else if (currToken.getToken().equals("True") || currToken.getToken().equals("False")) {

        }  else {
            // error
        }



        if (currToken.getTokenType() != TokenType.ID_KEYWORD && 
            !tokens.get(0).getToken().equals("True")  && 
            !tokens.get(0).getToken().equals("False") &&
            ) {

            }


        if (!tokens.get(0).getToken().equals("True") && !tokens.get(0).getToken().equals("False") ) {
            new ParsingError("Syntax Error", "Boolean Expression", currToken);
            return null;
        }

        return new BoolExp();
    } 
    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        return null;
    }

    /**
     * Will output a string of this tree in Java
     *
     * @param className
     * @return a string representing the Java code of this tree
     */
    @Override
    public String convertToJava(String className) {
        return null;
    }

    /**
     * Will output a string of this tree in C
     *
     * @return a string representing the C code of this tree
     */
    @Override
    public String convertToC() {
        return null;
    }

    /**
     * Will output a string of this tree in Python
     *
     * @return a string representing the Python code of this tree
     */
    @Override
    public String convertToPython() {
        return null;
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     *
     * @return true if valid Jott code; false otherwise
     */
    @Override
    public boolean validateTree() {
        return false;
    }
}