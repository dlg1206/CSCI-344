package provided.variables.expr;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;
import provided.variables.ops.Op;
import provided.variables.ops.RelOp;

public class Expression implements JottTree {
    JottTree lnode;
    Token operator;
    JottTree rnode;
    Boolean isTail;



    private Expression() {
        lnode = null;
        rnode = null;
        operator = null;
        isTail = false;
    }
    
    static BoolExp boolExp = null;
    static NumExp numExp = null;
    static StrExp strExp = null;
    
    
    
    static Token currToken;
    static String id;
    static RelOp relOp;
    static NumExp numExp2;

    public static Expression createExpression(ArrayList<Token> tokens) {
        currToken = tokens.get(0);
        if (currToken.getTokenType() == TokenType.STRING) {
            strExp = StrExp.parseStrExp(tokens);
        } else if (currToken.getToken().equals("True") ||
                   currToken.getToken().equals("True")) { 
            boolExp = BoolExp.parseBoolExp(tokens);    
        } else {
            numExp = NumExp.parseNumExp(tokens);
            relOp = RelOp.parseRelOp(tokens);
            if (relOp != null) {
                numExp2 = NumExp.parseNumExp(tokens);
            }

        }

        strExp = StrExp.parseStrExp(tokens);





        if (currToken.getTokenType() == TokenType.NUMBER) {
            numExp = NumExp.parseNumExp(tokens);
        } else if (currToken.getTokenType() == TokenType.STRING) {
            strExp = StrExp.parseStrExp(tokens);
        } else if (currToken.getToken().equals("True") ||
                   currToken.getToken().equals("True")) {
            boolExp = BoolExp.parseBoolExp(tokens);
        } else if (currToken.getTokenType() == TokenType.ID_KEYWORD) {
            // current is an id and we need more investigation
            tokens.remove(0);
            currToken = tokens.get(0);
            if (currToken.getToken().equals(";") || 
                currToken.getToken().equals(",") ||
                (Op.parseOp(tokens) != null)
            ) {
                // Only an id or id <op>
                numExp = NumExp.parseNumExp(tokens);
                currToken = tokens.get(0);
            }
            if (numExp != null) {
                if (RelOp.parseRelOp(tokens) != null) {
                    // We know that numExp is no longer correct so we destruct it.
                    numExp = null;
                    boolExp = BoolExp.parseBoolExp(tokens);
                }
            } else if (currToken.getToken().equals("[")) {
                // function call 
                numExp = NumExp.parseNumExp(tokens);
            }

        }   

        return new Expression();
    }


    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        if (isTail) {
            return lnode.convertToJott();
        } else {
            return lnode.convertToJott() + operator.getToken() + rnode.convertToJott();
        }
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