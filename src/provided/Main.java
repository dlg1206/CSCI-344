package provided;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        String filename = "providedExample3.jott";
        ArrayList<Token> tokens = JottTokenizer.tokenize("parserTestCases/" + filename);
        JottTree root = JottParser.parse(tokens);

        String jottCode = root.convertToJott();
        System.out.println("Resulting Jott Code:\n");
        System.out.println(jottCode);
        
    }
}
