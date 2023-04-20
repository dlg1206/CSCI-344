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
    public static WhileStmt parseWhileStmt(ArrayList<Token> tokens, int numIndent){
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
        
        currToken = tokens.get(0);
        if (!currToken.getToken().equals("]")){
            throw new ParsingError("Syntax", "3]", tokens.get(0));
        }
        tokens.remove(0);
        currToken = tokens.get(0);
        if (!currToken.getToken().equals("{")) {
            new ParsingError("Syntax Error", "{", currToken);
        }
        tokens.remove(0);

        wloop.body = Body.parseBody(tokens, numIndent);

        currToken = tokens.get(0);

        if (!currToken.getToken().equals("}")) {
            new ParsingError("Syntax Error", "}", currToken);
        }
        tokens.remove(0);
        

        return wloop;
    }

    @Override
    public String convertToJott() {
        return "while[" + conditions.convertToJott() + "] {\n" + body.convertToJott() + "\t}";
    }

    @Override
    public String convertToJava(String className) {
        return "while( " + conditions.convertToJava(className) + " ) {\n" + body.convertToJava(className) + "\t}";
    }

    @Override
    public String convertToC() {
        return "while( " + conditions.convertToC() + " ) {\n" + body.convertToC();
    }

    @Override
    public String convertToPython() {
        return "while " + conditions.convertToPython() + ":\n" + body.convertToPython() + "\n";
    }

    @Override
    public boolean validateTree() {
        if(conditions.validateTree() && body.validateTree()){
            return true;
        }
        return false;
    }
}
