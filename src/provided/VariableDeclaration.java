package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class VariableDeclaration implements JottTree{
    private final Type type;
    // todo add w/ Celeste's implementation
    // private final Id id;
    // todo add w/ future implementation
    // private final EndStatement end_statement

    // todo replace with correct
    // private VariableDeclaration(Type type, Id id, EndStatement end_statement)
    private VariableDeclaration(Type type){
        this.type = type;
        // this.id = id;
        // this.end_statement = end_statement;
    }

    public static VariableDeclaration parseVar_dec(ArrayList<Token> tokens){
        // Id id = createId(tokens);
        Type type = Type.parseType(tokens);
        // EndStatement end_statement = EndStatement.parseEnd_statement(tokens);
        return new VariableDeclaration(type);
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
