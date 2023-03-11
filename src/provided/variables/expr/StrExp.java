package provided.variables.expr;
import java.util.ArrayList;
import provided.Token;
import provided.TokenType;
import provided.variables.FunctionCall;
import provided.JottTree;
import provided.ParsingError;
public class StrExp implements JottTree{
    
    String strOrId = null;
    FunctionCall functionCall;

    public StrExp(String strOrId) {
        this.strOrId = strOrId;
    }
    public StrExp(FunctionCall funcCall) {
        this.functionCall = funcCall;
    }

    static Token currToken;

    public static StrExp parseStrExp(ArrayList<Token> tokens) {
        currToken = tokens.get(0);
        if (currToken.getTokenType() == TokenType.STRING) {
            
            String str = currToken.getToken();
            tokens.remove(0);
            return new StrExp(str);
        } else if (currToken.getTokenType() == TokenType.ID_KEYWORD) {
            Token lookAhead = tokens.get(1);
            if (lookAhead.getToken().equals("[")) {
                // Func Call
                return new StrExp(FunctionCall.parseFuncCall(tokens));
            } else {
                String id = currToken.getToken(); 
                tokens.remove(0);
                return new StrExp(id);
            }
        } else {
            throw new ParsingError("Syntax Error", "String Expression", currToken);
        }
    }

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        if (strOrId != null) return strOrId;
        return functionCall.convertToJott();
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
