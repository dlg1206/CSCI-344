package provided.variables.basics;

import provided.JottTree;
import provided.Token;
import java.util.ArrayList;

public class Type implements JottTree {
    
    public static String type;

    public Type(String type) {
        this.type = type;
    }

    public static Type parseType(ArrayList<Token> tokens) {
        Token currToken = tokens.get(0);
        if (currToken.equals("Boolean") || currToken.equals("String") ||
            currToken.equals("Integer") || currToken.equals("Double") 
        ) {
            tokens.remove(0);
            return new Type(currToken.getToken());
        }
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

