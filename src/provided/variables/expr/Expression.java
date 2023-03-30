package provided.variables.expr;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;
import provided.variables.ops.RelOp;

public class Expression implements JottTree {

    public JottTree exp1, exp2, exp3;
    
    public Expression(JottTree exp) {
        this.exp1 = exp;
    }

    public Expression(JottTree exp1, JottTree exp2, JottTree exp3) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }
    public static Token currToken;
    public static Expression parseExpression(ArrayList<Token> tokens) {
        currToken = tokens.get(0);
        if (currToken.getTokenType() == TokenType.STRING) {
            
            StrExp strExp = StrExp.parseStrExp(tokens);
            
            return new Expression(strExp);
        } else if (currToken.getToken().equals("True") ||
                   currToken.getToken().equals("False")) {
            BoolExp boolExp = BoolExp.parseBoolExp(tokens);    
            return new Expression(boolExp);
        } else {
            NumExp numExp = NumExp.parseNumExp(tokens);
            RelOp relOp = RelOp.parseRelOp(tokens);
            if (relOp != null) {
                NumExp numExp2 = NumExp.parseNumExp(tokens);
                return new Expression(numExp, relOp, numExp2);
            }
            return new Expression(numExp) ;
        }

    }


    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        if (exp3 != null && exp2 != null) return exp1.convertToJott() + exp2.convertToJott() + exp3.convertToJott();
        return exp1.convertToJott();
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