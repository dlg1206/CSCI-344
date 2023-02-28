package provided.expr;

import java.util.ArrayList;

import provided.ParsingError;
import provided.JottTree;
import provided.FunctionCall;
import provided.basics.Constant;
import provided.basics.Id;
import provided.Token;
import provided.TokenType;

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

    public static Expression createExpression(ArrayList<Token> tokens) throws ParsingError {
        Expression expr = new Expression();
        if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            if (tokens.get(1).getTokenType() == TokenType.L_BRACKET) {
                expr.lnode = FunctionCall.createFunctionCall(tokens);
            } else if (Character.isUpperCase(tokens.get(0).getToken().charAt(0))) {
                expr.lnode = Constant.CreateConstant(tokens);
            } else {
                expr.lnode = Id.CreateId(tokens);
            }
        } else if (tokens.get(0).getTokenType() == TokenType.NUMBER) {
            expr.lnode = Constant.CreateConstant(tokens);
        } else if (tokens.get(0).getTokenType() == TokenType.STRING) {
            expr.lnode = Constant.CreateConstant(tokens);
        } else {
            new ParsingError(tokens.get(0).getToken(), tokens.get(0).toString());
            return null;
        }
        if (tokens.get(0).getTokenType() == TokenType.MATH_OP || tokens.get(0).getTokenType() == TokenType.REL_OP) {
            if (!(tokens.get(1).getTokenType() == TokenType.ID_KEYWORD ||
                    tokens.get(1).getTokenType() == TokenType.NUMBER ||
                    tokens.get(1).getTokenType() == TokenType.STRING)) {
                new ParsingError(tokens.get(1).toString(), tokens.get(1).toString());
                return null;
            }
            expr.operator = tokens.remove(0);
            expr.rnode = Expression.createExpression(tokens);
        } else {
            expr.isTail = true;
        }


        return expr;
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