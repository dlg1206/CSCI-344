package provided;

import java.util.ArrayList;

public interface Else extends JottTree {

    static Else ParseElse(ArrayList<Token> tokens){

        if (tokens.get(0).toString().equals("{")){
            tokens.remove(0);
            // PARSE BODY HERE
        }

        return new Else() {
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
        }; //return the Else with added body
    }
}
