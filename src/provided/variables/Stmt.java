package provided.variables;

import provided.*;
import provided.variables.basics.Type;
import java.util.ArrayList;
import java.util.Stack;
import java.util.function.Function;

public class Stmt implements JottTree {

    EndStmt endStmt;
    Assignment assignment;
    VariableDeclaration variableDeclaration;
    FunctionCall functionCall;
    

    public Stmt() {}

    public Stmt(Assignment assignment, VariableDeclaration variableDeclaration, FunctionCall functionCall, EndStmt endStmt){
        this.assignment = assignment;
        this.variableDeclaration = variableDeclaration;
        this.functionCall = functionCall;
        this.endStmt = endStmt;
    }

    static Stmt parseStmt(ArrayList<Token> tokens, String functionCalling){
        if (Type.isType(tokens.get(0))){
            if (tokens.get(2).getToken().equals("=")){
                Assignment assignment = Assignment.parseAsmt(tokens, functionCalling);
                return new Stmt(assignment, null, null, null);
            } else {
                VariableDeclaration variableDeclaration = VariableDeclaration.parseVar_dec(tokens);
                return new Stmt(null, variableDeclaration, null, null);
            }
        } else if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
            if (tokens.get(1).getToken().equals("=")){
                Assignment assignment = Assignment.parseAsmt(tokens, functionCalling);
                return new Stmt(assignment, null, null, null);
            } else {
                FunctionCall functionCall = FunctionCall.parseFuncCall(tokens, functionCalling);
                EndStmt endStmt = EndStmt.parseEndStmt(tokens);
                return new Stmt(null, null, functionCall, endStmt);
            }
        }
        else {
            throw new ParsingError("Syntax Error", "Assignment, Variable Declaration, or Function Call", tokens.get(0));
        }
    }

    @Override
    public String convertToJott() {
        if (endStmt != null){
            return functionCall.convertToJott() + endStmt.convertToJott(); 
        }
        else{
            if(assignment != null){
                return assignment.convertToJott();
            }
            else if(variableDeclaration != null){
                return variableDeclaration.convertToJott();
            }
            else{
                return "";
            }
        }
    }


    @Override
    public String convertToJava(String className) {
        if (endStmt != null){
            return functionCall.convertToJava(className) + endStmt.convertToJava(className); 
        }
        else{
            if(assignment != null){
                return assignment.convertToJava(className);
            }
            else if(variableDeclaration != null){
                return variableDeclaration.convertToJava(className);
            }
            else{
                return "";
            }
        }
    }

    @Override
    public String convertToC() {

        // convert function call and satement
        if (endStmt != null)
            return functionCall.convertToC() + endStmt.convertToC();

        if(assignment != null)
            return assignment.convertToC();


        if(variableDeclaration != null)
            return variableDeclaration.convertToC();

        return null;    // prob err
    }

    @Override
    public String convertToPython() {
        if (endStmt != null){
            return functionCall.convertToPython() + endStmt.convertToPython(); 
        }
        else{
            if(assignment != null){
                return assignment.convertToPython();
            }
            else if(variableDeclaration != null){
                return variableDeclaration.convertToPython();
            }
            else{
                return "";
            }
        }
    }

    @Override
    public boolean validateTree() {
        if (endStmt != null){
            if(functionCall.validateTree() && endStmt.validateTree()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(assignment != null){
                return assignment.validateTree();
            }
            else if(variableDeclaration != null){
                return variableDeclaration.validateTree();
            }
            else{
                return false;
            }
        }    
    }
}
