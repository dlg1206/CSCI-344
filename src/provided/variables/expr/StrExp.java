package provided.variables.expr;
import java.util.ArrayList;
import provided.Token;
import provided.TokenType;
import provided.variables.FunctionCall;
import provided.JottTree;
public class StrExp implements JottTree{
    
    static String str;
    static String id;
    static FunctionCall functionCall;

    public StrExp() {}


    static Token currToken;

    public static StrExp parseStrExp(ArrayList<Token> tokens) {
        currToken = tokens.get(0);
        if (currToken.getTokenType() == TokenType.STRING) {
            str = currToken.getToken();
        } if (currToken.getTokenType() == TokenType.ID_KEYWORD) {
            id = currToken.getToken();
            Token lookAhead = tokens.get(1);
            if (lookAhead.getToken().equals("[")) {
                // Func Call
                id = null;
                functionCall = FunctionCall.parseFuncCall(tokens); 
            }
        }
        return new StrExp();
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
