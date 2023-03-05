package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class FunctionReturn implements JottTree{


    private final Type type;
    private FunctionReturn(Type type){
        // this.expr = createExpression
        this.type = type;
    }

    public static FunctionReturn parseFunction_return(ArrayList<Token> tokens){
        return new FunctionReturn(Type.parseType(tokens));
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
