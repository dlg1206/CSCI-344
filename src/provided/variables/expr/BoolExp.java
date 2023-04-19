package provided.variables.expr;

import provided.JottTree;
import provided.Token;
import provided.TokenType;
import provided.symtable.SymTable;
import provided.variables.FunctionCall;
import provided.variables.ops.RelOp;

import java.util.ArrayList;
import provided.ParsingError;

public class BoolExp implements JottTree {
    
    boolean IS_ID = false;   // todo always false?
    boolean IS_BOOL = false;
    boolean IS_NEXPR = false;
    boolean IS_FUNCCALL = false;
    

    String idOrBool;
    RelOp relOp = null; 
    NumExp numExp1;
    NumExp numExp2;
    FunctionCall functionCall;
    
    public BoolExp(String idOrBool, boolean isId) {
        
        this.IS_ID = isId;
        this.IS_BOOL = !isId;
        this.idOrBool = idOrBool;
    }

    public BoolExp(FunctionCall funcCall) {
        this.IS_FUNCCALL = true;
        this.functionCall = funcCall;
    }

    public BoolExp(NumExp numExp, RelOp relOp, NumExp numExp2) {
        this.IS_NEXPR = true;
        this.numExp1 = numExp;
        this.relOp = relOp;
        this.numExp2 = numExp2;
    }

    static Token currToken; 

    static public BoolExp parseBoolExp (ArrayList<Token> tokens) {
        currToken = tokens.get(0);
        if (currToken.getToken().equals("True") || currToken.getToken().equals("False")) {
            // is Bool
            String bool = currToken.getToken();
            tokens.remove(0);
            return new BoolExp(bool, false);
        } else if (currToken.getTokenType() == TokenType.ID_KEYWORD) {
            // ID or N_EXP or Func Call
            // , ; ]
            Token lookAhead = tokens.get(1);
            NumExp numExp1 = NumExp.parseNumExp(tokens);
            
            if (numExp1.functionCall != null) {
                RelOp relOp = RelOp.parseRelOp(tokens);
                
                if (relOp != null) {
                    NumExp numExp2 = NumExp.parseNumExp(tokens);
                    return new BoolExp(numExp1, relOp, numExp2);
                } else if (numExp1.nextNumExp == null){
                    return new BoolExp(numExp1.functionCall);
                } else {
                    throw new ParsingError("Syntax", "2Boolean Expression", currToken);
                }
            } else if (numExp1.idOrNum != null && numExp1.nextNumExp == null) {
                RelOp relOp = RelOp.parseRelOp(tokens);
                
                if (relOp != null) {
                    NumExp numExp2 = NumExp.parseNumExp(tokens);
                    return new BoolExp(numExp1, relOp, numExp2);
                }
                return new BoolExp(numExp1.idOrNum, true);
            } else {
                throw new ParsingError("Syntax", "1Boolean Expression", currToken);
            }
        } else {
            throw new ParsingError("Syntax", "0Boolean Expression", currToken);
        }
    } 
    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    @Override
    public String convertToJott() {
        if (IS_ID || IS_BOOL) return idOrBool;
        if (IS_NEXPR) return numExp1.convertToJott() + relOp.convertToJott() + numExp2.convertToJott();
        return numExp1.convertToJott();
    }

    /**
     * Will output a string of this tree in Java
     *
     * @param className
     * @return a string representing the Java code of this tree
     */
    @Override
    public String convertToJava(String className) {
        if (IS_ID || IS_BOOL) return idOrBool;
        if (IS_NEXPR) return numExp1.convertToJava(className) + relOp.convertToJava(className) + numExp2.convertToJava(className);
        return numExp1.convertToJava(className);
    }

    /**
     * Will output a string of this tree in C
     *
     * @return a string representing the C code of this tree
     */
    @Override
    public String convertToC() {
        return null;
    }

    /**
     * Will output a string of this tree in Python
     *
     * @return a string representing the Python code of this tree
     */
    @Override
    public String convertToPython() {
        if (IS_ID || IS_BOOL) return idOrBool;
        if (IS_NEXPR) return numExp1.convertToPython() + relOp.convertToPython() + numExp2.convertToPython();
        return numExp1.convertToPython();
    }

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     *
     * @return true if valid Jott code; false otherwise
     */
    @Override
    public boolean validateTree() {
        /*
        1: <id>
        2: <bool>
        3: <func_call>
        4: <n_expr> <op> <n_expr>
         */

        // 4: <n_expr> <op> <n_expr>
        if(numExp1 != null && relOp != null && numExp2 != null)
            return numExp1.validateTree() && relOp.validateTree() && numExp2.validateTree();

        // case 1 or 2
        if (idOrBool != null) {
            // 1: <id>
            if(SymTable.getVar(idOrBool) != null)   // check sym table
                return true;

            // 2: <bool>
            if(IS_BOOL)
                return true;
        }

        // 3: <func_call>
        if (functionCall != null)
            return functionCall.validateTree();

        // something went wrong
        return false;
    }
}