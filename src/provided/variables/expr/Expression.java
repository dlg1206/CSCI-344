package provided.variables.expr;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;
import provided.variables.ops.RelOp;
import provided.variables.basics.Type;

public class Expression implements JottTree {

    private static Type type;
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
            type = new Type("String");
            return new Expression(strExp);
        } else if (currToken.getToken().equals("True") ||
                   currToken.getToken().equals("False")) {
            BoolExp boolExp = BoolExp.parseBoolExp(tokens); 
            type = new Type("Boolean");
            return new Expression(boolExp);
        } else {
            NumExp numExp = NumExp.parseNumExp(tokens);
            RelOp relOp = RelOp.parseRelOp(tokens);
            if(numExp.idOrNum != null && numExp.idOrNum.contains(".")) {
                type = new Type("Double");
            }
            else{
                type = new Type("Integer");
            }
            if (relOp != null) {
                NumExp numExp2 = NumExp.parseNumExp(tokens);
                return new Expression(numExp, relOp, numExp2);
            }
            return new Expression(numExp) ;
        }

    }

    public Type getType() {
        return type;
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
        if (exp3 != null && exp2 != null) return exp1.convertToJava(className) + exp2.convertToJava(className) + exp3.convertToJava(className);
        return exp1.convertToJava(className);
    }

    /**
     * Will output a string of this tree in C
     *
     * @return a string representing the C code of this tree
     */
    @Override
    public String convertToC() {
        if (exp3 != null && exp2 != null) return exp1.convertToJott() + exp2.convertToJott() + exp3.convertToJott();
        return exp1.convertToJott();
    }

    /**
     * Will output a string of this tree in Python
     *
     * @return a string representing the Python code of this tree
     */
    @Override
    public String convertToPython() {
        if (exp3 != null && exp2 != null) return exp1.convertToPython() + exp2.convertToPython() + exp3.convertToPython();
        return exp1.convertToPython();
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     *
     * @return true if valid Jott code; false otherwise
     */
    @Override
    public boolean validateTree() {
        /*
        Case 1: expr1 = boolExp, expr2 = null, expr3 = null
        Case 2: expr1 = numExp, expr2 = null, expr3 = null
        Case 3: expr1 = strExp, expr2 = null, expr3 = null
        Case 4: expr1 = numExp, expr2 = relOp, expr3 = numExp
         */
        // all cases need to have exp1
        if (exp1 != null) {
            if (exp2 == null && exp3 == null) {
                // Case 1, 2, 3. bool/num/str have own validate functs
                return exp1.validateTree();
            } else {
                // Case 4
                return exp1.validateTree() && exp2.validateTree() && exp3.validateTree();
            }
        } else {
            // exp1 is null, there's a problem
            return false;    
        }   
    }
}