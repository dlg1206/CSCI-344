import java.io.FileWriter;
import java.io.IOException;

import provided.JottTokenizer;
import provided.JottParser;
import provided.JottTree;
import provided.Token;
import java.util.ArrayList;

public class Jott {
    
    private static String helpString = 
        "Usage: java JottCompiler <source> <target> [language]\n"
        + "\t<source>     The name of the file to be compiled.\n"
        + "\t<target>     The name of the file to be produced.\n"
        + "\t[language]:\n"
        + "\t\t\tThe language the source code should be compiled into.\n"
        + "\t\t\tDefault to the Jott language\n"
        + "\t\t\tOther options are C, Java, or Python\n";

    public static void main(String[] args) {
        if (args.length != 3 && args.length != 2) {
            System.out.println(helpString);
            return;
        } 

        String sourceFileName = args[0];
        String targetFileName = args[1];
        String targetLanguage = args.length > 2 ? args[2].toLowerCase() : "";
        
        ArrayList<Token> sourceResults = JottTokenizer.tokenize(sourceFileName);
        if (sourceResults == null) {
            System.err.println("There was an error while tokenizing the source language. \nSee above error message.");
            return;
        }

        JottTree root = JottParser.parse(sourceResults);

        if (root == null) {
            System.err.println("There was an error while parsing the tokenized source file. \nSee above error message.");
            return;
        }

        boolean isValidTree = root.validateTree();
        
        if (!isValidTree) {
            System.err.println("There was an error while validating the syntax of the source file. \nSee above error message.");
            return;
        }

        String targetProgram = switch(targetLanguage) {
            case "c" -> root.convertToC();
            case "java" -> root.convertToJava("Main");
            case "python" -> root.convertToPython(); 
            default -> root.convertToJott();
        };

        try {
            FileWriter outputFile = new FileWriter(targetFileName);
            outputFile.write(targetProgram);
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
