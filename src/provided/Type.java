package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class Type implements JottTree {

    private enum TYPE {
        DOUBLE,
        INTEGER,
        STRING,
        BOOLEAN,
        VOID
    }
    // default to void
    private TYPE type;
    private Type(TYPE type){
        this.type = type;
    }

    public static Type parseType(ArrayList<Token> tokens){
        Token token = tokens.remove(0); // pop list
        switch (token.getToken()){
            case "Double" -> {
                return new Type(TYPE.DOUBLE);
            }
            case "Integer" -> {
                return new Type(TYPE.INTEGER);
            }
            case "String" -> {
                return new Type(TYPE.STRING);
            }
            case "Boolean" -> {
                return new Type(TYPE.BOOLEAN);
            }
            case "Void" -> {
                return new Type(TYPE.VOID);
            }
        }
        // todo error?
        return null;
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
