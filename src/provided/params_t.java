package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class params_t implements JottTree{
    public static ArrayList<Token> parseParams_t(ArrayList<Token> tokens){
        try {
            // base case
            if(tokens.get(0).getToken().equals("]"))
                return tokens;

            tokens.remove(0);   // pop ","

            tokens = expr.parseExpr(tokens);
            tokens = params_t.parseParams_t(tokens);

            return tokens;

        } catch (IndexOutOfBoundsException e){
            /*
            Syntax Error:
            <Message>
            <filename>:<line_number>
            */

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
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
