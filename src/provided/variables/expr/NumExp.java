package provided.variables.expr;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.TokenType;
import provided.variables.FunctionCall;
import provided.variables.ops.Op;

import java.util.ArrayList;

public class NumExp implements JottTree{
    
    // static boolean IS_FUNCCALL = false;
    FunctionCall functionCall;
    Op op;
    NumExp nextNumExp;
    String idOrNum;

    public NumExp(String idOrNum) {
        this.idOrNum = idOrNum;
    }

    public NumExp(FunctionCall functionCall, Op op, NumExp nextNumExp) {
        this.functionCall = functionCall;
        this.op = op;
        this.nextNumExp = nextNumExp;
    }
    
    public NumExp(FunctionCall functionCall) {
        this.functionCall = functionCall;
    }

    public NumExp(String idOrNum, Op op, NumExp nextNumExp) {
        this.idOrNum = idOrNum;
        this.op = op;
        this.nextNumExp = nextNumExp;
    }

    static Token currToken;
    public static NumExp parseNumExp(ArrayList<Token> tokens) {
        currToken = tokens.get(0);
        
        if (currToken.getTokenType() == TokenType.ID_KEYWORD) {
            // tokens.remove(0);
            String id = currToken.getToken();
            Token lookAhead = tokens.get(1);
            if (lookAhead.getToken().equals("[")) {
                // Func Call
                id = null;
                FunctionCall functionCall = FunctionCall.parseFuncCall(tokens);
                Op op = Op.parseOp(tokens);
                if (op != null) {
                    NumExp nextNumExp = parseNumExp(tokens);
                    return new NumExp(functionCall, op, nextNumExp);
                } else {
                    return new NumExp(functionCall);
                }
            } else {
                tokens.remove(0);
                Op op = Op.parseOp(tokens);
                if (op != null) {
                    NumExp nextNumExp = parseNumExp(tokens);
                    return new NumExp(id, op, nextNumExp);
                }
                
                return new NumExp(currToken.getToken());
            }
            // Id
        } else if (currToken.getTokenType() == TokenType.NUMBER) {
            String num = currToken.getToken();
            tokens.remove(0);
            Op op = Op.parseOp(tokens);
            
            if (op != null) {
                NumExp nextNumExp = parseNumExp(tokens);
                return new NumExp(num, op, nextNumExp);
            }
            return new NumExp(num);
        } else {
            throw new ParsingError("Syntax Error", "Number Expression", currToken);
        }
    }

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        if (functionCall != null && nextNumExp != null) return functionCall.convertToJott() + op.convertToJott() + nextNumExp.convertToJott();
        if (functionCall != null) return functionCall.convertToJott();
        if (idOrNum != null && nextNumExp != null) return idOrNum + op.convertToJott() + nextNumExp.convertToJott();
         
        return idOrNum; 
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
         if (op != null && nextNumExp != null) {
            if (functionCall != null) {
                return functionCall.validateTree();
            } else if (idOrNum != null) {
                return true;    //  TODO: implement this check
            } else {
                return false;
            }
        } else if (functionCall != null) {
            return functionCall.validateTree();
        } else if (idOrNum != null) {
            return true;    //  TODO: implement this check
        } else {
            return false;
        }
    }
}
