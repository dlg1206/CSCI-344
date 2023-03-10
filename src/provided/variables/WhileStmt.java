package provided.variables;

import provided.JottTree;
import provided.Token;

import java.util.ArrayList;

public class WhileStmt implements JottTree {

    // TODO uncomment w/ implementation
//    ArrayList<bExpr> boolstmts;
    JottTree body;

    public WhileStmt(){
        // TODO uncomment w/ implementation
//        boolstmts = new ArrayList<bExpr>();
    }

    public static WhileStmt parseWhileStmt(ArrayList<Token> tokens){

        WhileStmt wloop = new WhileStmt();

        if (tokens.get(0).getToken().equals("while") | tokens.get(0).getToken().equals("While")){
            tokens.remove(0);
        }

        if (tokens.get(0).getToken().equals("(")){
            tokens.remove(0);
            // TODO uncomment w/ implementation
//            while (!tokens.get(0).getToken().equals(")")){
//                wloop.boolstmts.add(bExpr.parseBExpr(tokens));
//            }
            tokens.remove(0);
        }
        wloop.body = Body.parseBody(tokens);

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
