package provided.variables;

import provided.JottTree;
import provided.*;
import provided.variables.BodyStmt.StmtType;
import provided.variables.basics.Type;

import java.text.ParseException;
import java.util.ArrayList;

public class Body implements JottTree{

    ReturnStmt returnStmt;
    ArrayList<BodyStmt> bodyStmts;
    public boolean isRoot = false; //is this the main body in a function 
    int numIndent;

    public Body(ArrayList<BodyStmt> bodies, ReturnStmt returnStmt, int numIndent) {
        this.bodyStmts = bodies;
        this.returnStmt = returnStmt;
        this.numIndent = numIndent;
    } 

    static boolean isBodyStmt(Token currToken) {
        return ( Type.isType(currToken) || currToken.getTokenType() == TokenType.ID_KEYWORD || 
        currToken.getToken().equals("if") || currToken.getToken().equals("while") || 
        currToken.getToken().equals("return"));
    }

    static Token currToken;

    public static Body parseBody(ArrayList<Token> tokens, int numIndent, String functionCalling){
        currToken = tokens.get(0);
        ArrayList<BodyStmt> bodyStmts = new ArrayList<>();
        ReturnStmt returnStmt = null;
        
        if (currToken.getToken().equals("return")) {
            returnStmt = ReturnStmt.parseReturnStmt(tokens, numIndent + 1, functionCalling);
        } else if (
            Type.isType(currToken) || currToken.getTokenType() == TokenType.ID_KEYWORD || 
            currToken.getToken().equals("if") || currToken.getToken().equals("while")) {
            BodyStmt bodyStmt = BodyStmt.parseBodyStmt(tokens, numIndent + 1, functionCalling);
            currToken = tokens.get(0);
            if ( isBodyStmt(currToken) ) {
                Body recurBody = parseBody(tokens, numIndent, functionCalling);
                bodyStmts = recurBody.bodyStmts;
                returnStmt = recurBody.returnStmt;
            } 
            bodyStmts.add(0, bodyStmt);
        } else if (!currToken.getToken().equals("}")) {
            throw new ParsingError("Syntax Error", "id, type, if, while, or return", currToken);
        }
        return new Body(bodyStmts, returnStmt, numIndent);
    }

    @Override
    public String convertToJott() {
        String result = "";
        for (BodyStmt body: bodyStmts) {
            result += body.convertToJott() + "\n";
        }

        if (returnStmt != null){
            result += returnStmt.convertToJott();
        } 
        return result;
    }

    @Override
    public String convertToJava(String className) {
        String result = "";
        for (BodyStmt body: bodyStmts) {
            result += body.convertToJava(className);
        }

        if (returnStmt != null){
            result += returnStmt.convertToJava(className);
        }
        return result;
    }

    @Override
    public String convertToC() {

        StringBuilder bodyString = new StringBuilder();

        // append all body statements
        for (BodyStmt body: bodyStmts)
            bodyString.append(body.convertToC()).append("\n");

        // add return if one exists
        if (returnStmt != null)
            bodyString.append(returnStmt.convertToC());

        return bodyString.toString();
    }

    @Override
    public String convertToPython() {

        String result = "";
        for (BodyStmt body: bodyStmts) {
            result += body.convertToPython() + "\n";
        }

        if (returnStmt != null){
            result += returnStmt.convertToPython();
        } else if (this.numIndent == 0 && bodyStmts.isEmpty()) {
            result += new ReturnStmt(null, new EndStmt(), 1).convertToPython();
        }
        return result;
    }

    @Override
    public boolean validateTree() {
        for(BodyStmt b : bodyStmts){
            if(b.validateTree() == false){
                return false;
            }
        }
        /**
         *  if(isRoot){
            if(isReturnable() != null){
                return returnStmt.validateTree();
            }
            else{
                return false;
            }
        }
         */
        if(returnStmt != null){
            return returnStmt.validateTree();
        }
        return true;

    }

    /**
     * 
     * check if body has a return and the return type
     * @return the return type
     */
    public Type isReturnable(){
        if(returnStmt != null){
            return returnStmt.isReturnable();
        }
        //check for return statement contained in if/else
        boolean allIfElse = true;
        boolean ifHasReturn = true;
        Type ifReturnType = null; //return type in if stmts 
        for(BodyStmt b : bodyStmts){
            if(b.type != StmtType.IF){
                allIfElse = false;
            }
            else{
                Type t = b.ifStmt.isReturnable(); //if stmt return type
                if(t == null){
                    ifHasReturn = false; //one of the if stmts doesn't have a return 
                }
                if(ifReturnType == null){ //return type not set yet
                    ifReturnType = new Type(t.type);
                }
                else{ //if return type has been set 
                    if(!ifReturnType.equals(t)){
                        ifHasReturn = false; //if/else statements w/ diff return types, syntax error 
                    }
                }
            }
        }
        if(allIfElse){ //body is made 100% of if/else
            if(ifHasReturn){
                return ifReturnType; //all if/else has return 
            }
            else{
                return null; //one of the if/else is missing return or diff type, invalid 
            }
        } 
        return null;
    }

    /**
     * called if this is the root body in a function
     * go through children and look to check that we're returning 
     * @return
     */
    private boolean checkForReturn(){
        if(returnStmt != null){
            return true;
        }
        //check for return statement contained in if/else
        boolean allIfElse = true;
        boolean ifHasReturn = false;
        for(BodyStmt b : bodyStmts){
            if(b.type != StmtType.IF){
                allIfElse = false;
            }
            else{
                if(b.ifStmt.isReturnable() == null){
                    ifHasReturn = false;
                }
            }
        }
        if(allIfElse){
            if(ifHasReturn){
                return true;
            }
            else{
                return false;
            }
        } 
        return false;
    }

    /**
     * set this body to be the root of the function
     */
    public void setRoot(){
        isRoot = true;
    }
}
