package provided;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        String filename = "largerValid.jott";
        ArrayList<Token> tokens = JottTokenizer.tokenize("phase3testCases/" + filename);
        JottParser.parse(tokens);
    }
}
