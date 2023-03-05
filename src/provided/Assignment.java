package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class Assignment implements JottTree{

    private final Type type;
    // todo add w/ Celeste's implementation
    // private final Id id;
    // todo add w/ Celeste's implementation
    // private final Expression expr;
    // todo add w/ future implementation
    // private final EndStatement end_statement


    // todo replace with correct
    // private Assignment(Type type, Id id, Expression expr, EndStatement end_statement)
    private Assignment(Type type){
        this.type = type;
        // this.id = id;
        // this.expr = expr;
        // this.end_statement = end_statement;
    }

    public static Assignment parseAsmt(ArrayList<Token> tokens){
        Type type = Type.parseType(tokens);
        // Id id = Id.createId(tokens);
        tokens.remove(0);   // pop '='
        // Expression expr = Expression.createExpression(tokens);
        // EndStatement end_statement = EndStatement.parseEnd_statement(tokens);
        // return new Assignment(type, id, expr, end_statement);
        return new Assignment(type);
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
