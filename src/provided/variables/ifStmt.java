package provided.variables;

import provided.JottTree;
import provided.ParsingError;
import provided.Token;

import java.util.ArrayList;

public class IfStmt implements JottTree { // will need to extend body statement

    ArrayList<bExpr> boolstmts;
    Body body;
    ElseIfLst elifLst;
    Else elseStmt;

    public IfStmt(){
        boolstmts = new ArrayList<bExpr>();
        body = null;
        elifLst = null;
        elseStmt = null;
    }


    public static IfStmt parseIfStmt(ArrayList<Token> tokens) {
        IfStmt IfStmt = new IfStmt();
        boolean elif = false;
        if (tokens.get(0).getToken().equals("if") | tokens.get(0).getToken().equals("If")){
            tokens.remove(0);
            elif = false;
        }
        else if (tokens.get(0).getToken().equals("Elif") | tokens.get(0).getToken().equals("elif")){
            tokens.remove(0);
            elif = true;
        }
        if (tokens.get(0).getToken().equals("(")){
            tokens.remove(0);
            while (!tokens.get(0).getToken().equals(")")){
                IfStmt.boolstmts.add(bExpr.parseBExpr(tokens));
            }
            tokens.remove(0);
        }
        else {
            new ParsingError("Syntax", "(", tokens.get(0));
            // error, no open parenthesis after if
            return null;
        }

        if (tokens.get(0).getToken().equals("{")){
            tokens.remove(0);
            IfStmt.body = Body.parseBody(tokens);
            if (tokens.get(0).getToken().equals("}")) {
                tokens.remove(0);
            }
            else{
                new ParsingError("Syntax", "}", tokens.get(0));
            }
        }
        else {
            new ParsingError("Syntax", "{", tokens.get(0));
            // error, no open bracket after if
            return null;
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
