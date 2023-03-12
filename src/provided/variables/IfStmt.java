package provided.variables;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;
import provided.variables.expr.BoolExp;

import java.util.ArrayList;

public class IfStmt implements JottTree { // will need to extend body statement

    BoolExp boolexp;
    Body body;
    ElseIfLst elifLst;
    Else elseStmt;
    boolean elif;

    public IfStmt(boolean elif){
        body = null;
        elifLst = null;
        elseStmt = null;
        elif = true;
    }

    public static IfStmt parseIfStmt(ArrayList<Token> tokens) {
        
        boolean elif = false;
        if (tokens.get(0).getToken().equals("if")){
            tokens.remove(0);
            elif = false;
        }
        else if (tokens.get(0).getToken().equals("elseif")){
            tokens.remove(0);
            elif = true;
        }
        IfStmt IfStmt = new IfStmt(elif);
        if (tokens.get(0).getToken().equals("[")){
            tokens.remove(0);
            IfStmt.boolexp = BoolExp.parseBoolExp(tokens);
            if (tokens.get(0).getToken().equals("]")){
                tokens.remove(0);
            }
            else{
                throw new ParsingError("Syntax", "2]", tokens.get(0));
            }
        }
        else {
            throw new ParsingError("Syntax", "[", tokens.get(0));
        }

        if (tokens.get(0).getToken().equals("{")){
            tokens.remove(0);
            IfStmt.body = Body.parseBody(tokens);
            if (tokens.get(0).getToken().equals("}")) {
                tokens.remove(0);
            }
            else{
                throw new ParsingError("Syntax", "}", tokens.get(0));
            }
        }
        else {
            throw new ParsingError("Syntax", "{", tokens.get(0));
        }

        if (!elif) {
            if (tokens.get(0).getToken().equals("elseif")) {
                tokens.remove(0);
                IfStmt.elifLst = ElseIfLst.ParseElseIfLst(tokens);
            }

            if (tokens.get(0).getToken().equals("else")) {
                tokens.remove(0);
                IfStmt.elseStmt = Else.ParseElse(tokens);
            }
        }
        // check token after the end of the body, check for an else/else if loop until no more or an else
        return IfStmt; // return a new instance of ifstmt with all parts added to it
    }

    @Override
    public String convertToJott() {
        if (!elif) {
            return "if[" + boolexp.convertToJott() + "]{" + body.convertToJott() + "}" + elifLst.convertToJott() + elseStmt.convertToJott();
        }
        else {
            return "if[" + boolexp.convertToJott() + "]{" + body.convertToJott() + "}";
        }
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
