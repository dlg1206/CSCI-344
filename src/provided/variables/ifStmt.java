package provided;

import provided.variables.Body;
import provided.variables.Else;

import java.util.ArrayList;

public class ifStmt implements JottTree { // will need to extend body statement

    ArrayList<bExpr> boolstmts;
    Body body;
    ElseIfLst elifLst;
    Else elseStmt;

    public ifStmt(){
        boolstmts = new ArrayList<bExpr>();
        body = null;
        elifLst = null;
        elseStmt = null;
    }


    public static ifStmt parseIfStmt(ArrayList<Token> tokens) {
        ifStmt IfStmt = new ifStmt();

        if (tokens.get(0).toString().equals("if") | tokens.get(0).toString().equals("If")){
            tokens.remove(0);
        }

        if (tokens.get(0).toString().equals("(")){
            tokens.remove(0);
            while (!tokens.get(0).toString().equals(")")){
                IfStmt.boolstmts.add(bExpr.parseBExpr(tokens));
            }
            tokens.remove(0);
        }
        else {
            // error, no open parenthesis after if
            return null;
        }

        if (tokens.get(0).toString().equals("{")){
            tokens.remove(0);
            IfStmt.body = Body.parseBody(tokens);
        }
        else {
            // error, no open bracket after if
            return null;
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
