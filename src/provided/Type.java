package provided;

import java.util.ArrayList;

/**
 * @author Derek Garcia
 **/

public class Type implements JottTree {

    public enum TYPE {
        DOUBLE,
        INTEGER,
        STRING,
        BOOLEAN
    }
    private final TYPE type;
    public Type(TYPE type){
        this.type = type;
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
