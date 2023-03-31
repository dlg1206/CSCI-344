package provided.variables;

import provided.JottTree;
import provided.Token;
import provided.variables.basics.Type;

import java.util.ArrayList;

public class Else implements JottTree {

    Body body;

    public Else(){
        body = null;
    }

    static Else ParseElse(ArrayList<Token> tokens){

        Else elseStmt = new Else();
        if (tokens.get(0).getToken().equals("{")){
            tokens.remove(0);
            elseStmt.body = Body.parseBody(tokens);
            tokens.remove(0);
            // may need to remove an additional token if } was left in by body parse
        }
        return elseStmt;
    }

    @Override
    public String convertToJott() {
        return "Else{" + body.convertToJott() + "}";
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
        return body.validateTree();
    }

    public Type isReturnable(){
        return body.isReturnable();
    }
}
