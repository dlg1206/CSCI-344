package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class params implements JottTree{

    /*
    above is just while != ]
        parseParam tokens
     */
    static params parseParams(ArrayList<Token> tokens) throws Exception {
        // < params > -> < expr > < params_t >
        try {
            for(;;){

                Token curToken = tokens.remove(0);

                if(curToken.getToken().equals("]"))
                    break;
                params_t.parseParams_t(tokens);

            }


//            return ;
        } catch (IndexOutOfBoundsException e){
            /*
            Syntax Error:
            <Message>
            <filename>:<line_number>
            */
            throw new Exception("Failed to parse params");
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
