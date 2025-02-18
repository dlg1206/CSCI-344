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

    static Else ParseElse(ArrayList<Token> tokens, int numIndent, String functionCalling){

        Else elseStmt = new Else();
        if (tokens.get(0).getToken().equals("{")){
            tokens.remove(0);
            elseStmt.body = Body.parseBody(tokens, numIndent, functionCalling);
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
        return "else{" + body.convertToJava(className) + "}";
    }

    @Override
    public String convertToC() {
        return "else {\n" + body.convertToC() + "\n}\n";
    }

    @Override
    public String convertToPython() {
        return "else:\n" + body.convertToPython();
    }

    @Override
    public boolean validateTree() {
        return body.validateTree();
    }

    public Type isReturnable(){
        return body.isReturnable();
    }
}
