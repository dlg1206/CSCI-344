package provided;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        String filename = "1foo.jott";
        ArrayList<Token> tokens = JottTokenizer.tokenize("parserTestCases/providedExample3.jott");
        JottParser.parse(tokens);
    }
}
