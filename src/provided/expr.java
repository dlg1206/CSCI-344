package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class expr implements JottTree {

    public static ArrayList<Token> parseExpr(ArrayList<Token> tokens) throws Exception {
        // < params > -> < expr > < params_t >
        try {
            // todo implement
            tokens.remove(0);
            return tokens;

        } catch (IndexOutOfBoundsException e){
            /*
            Syntax Error:
            <Message>
            <filename>:<line_number>
            */
            throw new Exception("Failed to parse params");
        }

    }

    @Override
    public String convertToJott() {
        return null;
    }

    @Override
    public String convertToJava(String className) {
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
