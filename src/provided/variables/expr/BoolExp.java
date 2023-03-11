package provided.variables.expr;

import provided.JottTree;
import provided.Token;
import provided.TokenType;
import provided.variables.FunctionCall;
import provided.variables.ops.RelOp;

import java.util.ArrayList;
import provided.ParsingError;

public class BoolExp implements JottTree {
    
    static boolean IS_ID = false;
    static boolean IS_BOOL = false;
    static boolean IS_NEXPR = false;
    static boolean IS_FUNCCALL = false;
    

    static String id;
    static RelOp relOp = null; 
    static String bool; 
    static NumExp numExp1;
    static NumExp numExp2;
    
    public BoolExp() {}

    static Token currToken; 

    static public BoolExp parseBoolExp (ArrayList<Token> tokens) {

        currToken = tokens.get(0);

        if (currToken.getToken().equals("True") || currToken.getToken().equals("False")) {
            // is Bool
            IS_BOOL = true;
            bool = currToken.getToken();
            tokens.remove(0);
        } else if (currToken.getTokenType() == TokenType.ID_KEYWORD) {
            // ID or N_EXP or Func Call
            // , ; ]
            Token lookAhead = tokens.get(1);
            if (lookAhead.getToken().equals(";") || lookAhead.getToken().equals(",") || lookAhead.getToken().equals("]")) {
                // Just Id
                IS_ID = true;
                id = currToken.getToken();
                tokens.remove(0);
            } else if (lookAhead.getToken().equals("[")) {
                numExp1 = NumExp.parseNumExp(tokens);
                relOp = RelOp.parseRelOp(tokens);
                if (relOp != null) {
                    IS_NEXPR = true;
                    numExp2 = NumExp.parseNumExp(tokens);
                } else if (numExp1.IS_FUNCCALL) {
                    IS_FUNCCALL = true;
                }
            } else {
                // Throw Error
            }
        }  else {
            // Throw Error
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