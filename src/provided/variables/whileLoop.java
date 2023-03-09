package provided.variables;

import provided.JottTree;
import provided.Token;

import java.util.ArrayList;

public class whileLoop implements JottTree {

    ArrayList<bExpr> boolstmts;
    JottTree body;

    public whileLoop(){
        boolstmts = new ArrayList<bExpr>();
    }

    public static whileLoop parseWhileLoop(ArrayList<Token> tokens){

        whileLoop wloop = new whileLoop();

        if (tokens.get(0).toString().equals("while") | tokens.get(0).toString().equals("While")){
            tokens.remove(0);
        }

        if (tokens.get(0).toString().equals("(")){
            tokens.remove(0);
            while (!tokens.get(0).toString().equals(")")){
                wloop.boolstmts.add(bExpr.parseBExpr(tokens));
            }
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
