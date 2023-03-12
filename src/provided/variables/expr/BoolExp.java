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
    

    String idOrBool;
    RelOp relOp = null; 
    NumExp numExp1;
    NumExp numExp2;
    FunctionCall functionCall;
    
    public BoolExp(String idOrBool) {
        this.idOrBool = idOrBool;
    }

    public BoolExp(FunctionCall funcCall) {
        this.functionCall = funcCall;
    }

    public BoolExp(NumExp numExp, RelOp relOp, NumExp numExp2) {
        this.numExp1 = numExp;
        this.relOp = relOp;
        this.numExp2 = numExp2;
    }

    static Token currToken; 

    static public BoolExp parseBoolExp (ArrayList<Token> tokens) {
        currToken = tokens.get(0);
        if (currToken.getToken().equals("True") || currToken.getToken().equals("False")) {
            // is Bool
            IS_BOOL = true;
            String bool = currToken.getToken();
            tokens.remove(0);
            return new BoolExp(bool);
        } else if (currToken.getTokenType() == TokenType.ID_KEYWORD) {
            // ID or N_EXP or Func Call
            // , ; ]
            Token lookAhead = tokens.get(1);
            NumExp numExp1 = NumExp.parseNumExp(tokens);
            System.out.println("NUM: " + numExp1.convertToJott());
            if (numExp1.functionCall != null) {
                RelOp relOp = RelOp.parseRelOp(tokens);
                System.out.println("RELOP: " + relOp);
                if (relOp != null) {
                    IS_NEXPR = true;
                    NumExp numExp2 = NumExp.parseNumExp(tokens);
                    return new BoolExp(numExp1, relOp, numExp2);
                } else if (numExp1.nextNumExp == null){
                    return new BoolExp(numExp1.functionCall);
                } else {
                    throw new ParsingError("Syntax", "2Boolean Expression", currToken);
                }
            } else if (numExp1.idOrNum != null && numExp1.nextNumExp == null) {
                RelOp relOp = RelOp.parseRelOp(tokens);
                System.out.println("RELOP: " + relOp.convertToJott());
                if (relOp != null) {
                    IS_NEXPR = true;
                    NumExp numExp2 = NumExp.parseNumExp(tokens);
                    return new BoolExp(numExp1, relOp, numExp2);
                }
                return new BoolExp(numExp1.idOrNum);
            } else {
                throw new ParsingError("Syntax", "1Boolean Expression", currToken);
            }
        } else {
            throw new ParsingError("Syntax", "0Boolean Expression", currToken);
        }
    } 
    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        if (IS_ID || IS_BOOL) return idOrBool;
        if (IS_NEXPR) return numExp1.convertToJott() + relOp.convertToJott() + numExp2.convertToJott();
        return numExp1.convertToJott();
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