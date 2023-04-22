import provided.JottParser;
import provided.JottTokenizer;
import provided.JottTree;
import provided.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PhaseFourTester {

    private static final ArrayList<String> DEFAULT_TESTS = new ArrayList<>(){{
        //add("parserTestCases/helloWorld.jott");
        //add("parserTestCases/providedExample1.jott");
        //add("parserTestCases/validLoop.jott");
        //add("phase3testcases/ifStmtReturns.jott");
        add("phase3testcases/reallyLong.jott");
    }};

    static private String test(String sourceFilePath, String targetLanguage) throws Exception {

        ArrayList<Token> sourceResults = JottTokenizer.tokenize(sourceFilePath);
        if (sourceResults == null)
            throw new Exception("There was an error while tokenizing the source language. \nSee above error message.");

        JottTree root = JottParser.parse(sourceResults);

        if (root == null)
            throw new Exception("There was an error while parsing the tokenized source file. \nSee above error message.");

        boolean isValidTree = true;

        if (!isValidTree)
            throw new Exception("There was an error while validating the syntax of the source file. \nSee above error message.");

        return switch (targetLanguage) {
            case "c" -> root.convertToC();
            case "java" -> root.convertToJava("Main");
            case "python" -> root.convertToPython();
            default -> root.convertToJott();
        };
    }


    public static void main(String[] args) {

        ArrayList<String> testFiles = new ArrayList<>();
        if(args.length != 2){
            System.out.println("No file given, using defaults");
            testFiles = DEFAULT_TESTS;
        } else {
            System.out.println("Using file \"" + args[1] + "\"");
            testFiles.add(args[1]);
        }

        for(String testFile : testFiles){
            System.out.println("Running Test with: " + testFile);

            try{
                StringBuilder in = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(testFile));
                String line;
                // read in target
                while((line = br.readLine()) != null)
                    in.append(line).append("\n");
                br.close();

                String out = test(testFile, args[0]);

                System.out.println("IN");
                System.out.println(in);
                System.out.println("OUT");
                System.out.println(out);

            } catch (Exception e){
                System.out.println("\tTest Failed, reason: " + e.getMessage());
            }
        }


    }
}
