package provided.variables;

import provided.JottTree;
import provided.Token;

import java.util.ArrayList;

public class WhileStmt implements JottTree {

//    ArrayList<bExpr> boolstmts;
    JottTree body;

    public WhileStmt(){
        // TODO uncomment w/ implementation
//        boolstmts = new ArrayList<bExpr>();
    }
    static Token currToken;
    public static WhileStmt parseWhileStmt(ArrayList<Token> tokens){
        // We checked while outside of this call so we remove the currToken
        tokens.remove(0);
        currToken = tokens.get(0);
        if (!currToken.getToken().equals("(")){
            // Throw error
            return null;
        }
        tokens.remove(0);
        // Check boolean
        currToken.get(0);
        
        if ()
        wloop.body = Body.parseBody(tokens);

        return new WhileStmt();
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
