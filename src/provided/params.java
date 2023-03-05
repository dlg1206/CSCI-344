package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class params implements JottTree{


    // Temp testing funct
    public static void main(String[] args) throws Exception {
        ArrayList<Token> params = new ArrayList<>();
        params.add(new Token("[", "foo", 0, TokenType.L_BRACKET));
        params.add(new Token("5", "foo", 0, TokenType.NUMBER));
        params.add(new Token(",", "foo", 0, TokenType.COMMA));
        params.add(new Token("7", "foo", 0, TokenType.NUMBER));
        params.add(new Token("]", "foo", 0, TokenType.R_BRACKET));

        parseParams(params);

    }
    public static ArrayList<Token> parseParams(ArrayList<Token> tokens) throws Exception {
        try {
            tokens.remove(0);   // pop "["

            tokens = expr.parseExpr(tokens);
            tokens = params_t.parseParams_t(tokens);

            tokens.remove(0);   // pop "]"
            return tokens;

        } catch (IndexOutOfBoundsException e){
            /*
            todo err msgs
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
