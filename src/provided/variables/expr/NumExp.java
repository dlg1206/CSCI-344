package provided.variables.expr;
import java.util.ArrayList;
import provided.Token;
import provided.TokenType;
import provided.variables.FunctionCall;
import provided.variables.function.FunctionDefParams;
import provided.variables.ops.Op;
import provided.JottTree;

public class NumExp implements JottTree{
    
    static boolean IS_FUNCCALL = false;
    static String id;
    static FunctionCall functionCall;
    static Op op;
    static NumExp nextNumExp;
    static String num;


    public NumExp() {

    }

    static Token currToken;
    public static NumExp parseNumExp(ArrayList<Token> tokens) {
        currToken = tokens.get(0);
        if (currToken.getTokenType() == TokenType.ID_KEYWORD) {
            id = currToken.getToken();
            // tokens.remove(0);
            Token lookAhead = tokens.get(1);
            if (lookAhead.getToken().equals("[")) {
                // Func Call
                id = null;
                IS_FUNCCALL = true;
                functionCall = FunctionCall.parseFuncCall(tokens);
                op = Op.parseOp(tokens);
                if (op != null) {
                    IS_FUNCCALL = false;
                    nextNumExp = parseNumExp(tokens);
                }
            } else {
                tokens.remove(0);
                op = Op.parseOp(tokens);
                if (op != null) {
                    nextNumExp = parseNumExp(tokens);
                }
            }
            return new NumExp();
        } else if (currToken.getTokenType() == TokenType.NUMBER) {
            num = currToken.getToken();
            tokens.remove(0);
            op = Op.parseOp(tokens);
            if (op != null) {
                nextNumExp = parseNumExp(tokens);
            }
            return new NumExp();
        } else {
            // Throw Error
        }
        return null;
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
