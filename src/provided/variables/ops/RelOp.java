package provided.variables.ops;

import java.util.ArrayList;
import provided.Token;
import provided.JottTree;

public class RelOp implements JottTree {
    
    public String relOpType;
    public RelOp(String relOpType) {
        this.relOpType = relOpType;
    }

    public static RelOp parseRelOp(ArrayList<Token> tokens) {
        Token currToken = tokens.get(0);
        if (currToken.getToken().equals(">") || currToken.getToken().equals(">=") ||
            currToken.getToken().equals("<") || currToken.getToken().equals("<=") ||
            currToken.getToken().equals("==") || currToken.getToken().equals("!=")
        ) {
            tokens.remove(0);
            return new RelOp(currToken.getToken());
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