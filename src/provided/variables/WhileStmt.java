package provided.variables;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.variables.expr.BoolExp;

import java.util.ArrayList;

public class WhileStmt implements JottTree {

    BoolExp conditions;
    JottTree body;

    public WhileStmt(){ }

    static Token currToken;
    public static WhileStmt parseWhileStmt(ArrayList<Token> tokens){
        // We checked while outside of this call so we remove the currToken
        WhileStmt wloop = new WhileStmt();
        tokens.remove(0);
        currToken = tokens.get(0);
        if (!currToken.getToken().equals("[")){
            throw new ParsingError("Syntax", "[", currToken);
        }
        tokens.remove(0);
        // Check boolean
        wloop.conditions = BoolExp.parseBoolExp(tokens);
        if (!tokens.get(0).getToken().equals("]")){
            throw new ParsingError("Syntax", "]", tokens.get(0));
        }
        tokens.remove(0);
        currToken = tokens.get(0);
        wloop.body = Body.parseBody(tokens);

        return wloop;
    }

    @Override
    public String convertToJott() {
        return "while[" + conditions.convertToJott() + "]{" + body.convertToJott() + "}";
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
