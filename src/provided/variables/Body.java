package provided.variables;

import provided.JottTree;
import provided.*;
import provided.variables.basics.Type;

import java.text.ParseException;
import java.util.ArrayList;

public class Body implements JottTree{

    ReturnStmt returnStmt;
    ArrayList<BodyStmt> bodyStmts;

    public Body(ArrayList<BodyStmt> bodies, ReturnStmt returnStmt) {
        this.bodyStmts = bodies;
        this.returnStmt = returnStmt;
    } 

    static boolean isBodyStmt(Token currToken) {
        return ( Type.isType(currToken) || currToken.getTokenType() == TokenType.ID_KEYWORD || 
        currToken.getToken().equals("if") || currToken.getToken().equals("while") || 
        currToken.getToken().equals("return"));
    }

    static Token currToken;
    public static Body parseBody(ArrayList<Token> tokens){
        currToken = tokens.get(0);
        ArrayList<BodyStmt> bodyStmts = new ArrayList<>();
        ReturnStmt returnStmt = null;

        if (currToken.getToken().equals("return")) {
            returnStmt = ReturnStmt.parseReturnStmt(tokens);
        } else if (
            Type.isType(currToken) || currToken.getTokenType() == TokenType.ID_KEYWORD || 
            currToken.getToken().equals("if") || currToken.getToken().equals("while")) {
            BodyStmt bodyStmt = BodyStmt.parseBodyStmt(tokens);

            if ( isBodyStmt(currToken) ) {
                Body recurBody = parseBody(tokens);
                bodyStmts = recurBody.bodyStmts;
                returnStmt = recurBody.returnStmt;
            } 
            bodyStmts.add(0, bodyStmt);
        } else if (!currToken.getToken().equals("}")) {
            throw new ParsingError("Syntax Error", "id, type, if, while, or return", currToken);

        return new Body(bodyStmts, returnStmt);
    }

    @Override
    public String convertToJott() {
        String result = "";
        for (BodyStmt body: bodyStmts) {
            result += "\t" + body.convertToJott() + "\n";
        }

        if (returnStmt != null){
            result += "\t" + returnStmt.convertToJott();
        } 
        return result;
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
