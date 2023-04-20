package provided.variables.basics;

import provided.JottTree;
import provided.Token;
import java.util.ArrayList;

public class Type implements JottTree {
    
    public String type;

    public Type(String type) {
        this.type = type;
    }

    public static boolean isType(Token token) {
        return (token.getToken().equals("Boolean") || token.getToken().equals("String") ||
        token.getToken().equals("Integer") || token.getToken().equals("Double"));
    }

    public static Type parseType(ArrayList<Token> tokens) {
        Token currToken = tokens.get(0);
        if (isType(currToken)) {
            tokens.remove(0);
            return new Type(currToken.getToken());
        }
        return null;
    }

    @Override
    public String convertToJott() {
        return type;
    }

    @Override
    public String convertToJava(String className) {
        return type;
    }

    @Override
    public String convertToC() {
        // Assign DT based on Type
        switch (this.type) {
            // No native boolean type in C, 1 or 0
            // regular int
            case "Boolean", "Integer" -> {
                return "int";
            }
            case "String" -> {
                return "char";  // to be used for char array
            }
            case "Double" -> {
                return "double";
            }
            case "Void" -> {
                return "void";
            }
        }
        return null;    // prob cause error
    }

    @Override
    public String convertToPython() {
        return type;
    }

    @Override
    public boolean validateTree() {
        if(type.equals("Boolean") || type.equals("String") || type.equals("Integer") || type.equals("Double") || type.equals("Void")){
            return true;
        }
        return false;
    }

    public boolean equals(Type t2){
        if(this.type.equals(t2.type)){
            return true;
        }
        return false;
    }
}

