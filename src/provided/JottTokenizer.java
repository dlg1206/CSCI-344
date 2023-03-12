
package provided;

/**
 * This class is responsible for tokenizing Jott code.
 *
 * @author Derek Garcia (dlg1206), Zoe Wheatcroft (zjw1614), Celeste Gambardella (cg7346), Nick Hunt (nrh9573), Josh Cain (jbc2758)
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JottTokenizer {

    private static String globalFileName;


    private static TokenType getTokenType(char currChar) {

        TokenType type = switch (currChar) {
            case ',' -> TokenType.COMMA;
            case ']' -> TokenType.R_BRACKET;
            case '[' -> TokenType.L_BRACKET;
            case '}' -> TokenType.R_BRACE;
            case '{' -> TokenType.L_BRACE;
            case ';' -> TokenType.SEMICOLON;
            case ':' -> TokenType.COLON;
            // All math tokens are 'mathOp' tokens
            case '/', '+', '-', '*' -> TokenType.MATH_OP;
            default -> null;
        };
        if (type == null) {
            if (currChar == '=') {
                type = TokenType.ASSIGN;
            } else if (currChar == '<' || currChar == '>' || currChar == '!') {
                type = TokenType.REL_OP;
            } else if (currChar == '.') {
                type = TokenType.NUMBER;
            } else if (currChar >= '0' && currChar <= '9') {
                type = TokenType.NUMBER;
            } else if (Character.toLowerCase(currChar) >= 'a'
                    && Character.toLowerCase(currChar) <= 'z') {
                type = TokenType.ID_KEYWORD;
            } else if (currChar == '"') {
                type = TokenType.STRING;
            } else {
                // throw error
            }
        }
        return type; //shouldn't get here
    }

    //TOKEN HANDLER FUNCTIONS
    //each function should return an int of how many characters it looked ahead
    //gl lad

    private static void handleSingleCharToken(String symbol, int lineNum,
                                              ArrayList<Token> tokenList, TokenType type) {
        tokenList.add(new Token(symbol, globalFileName, lineNum, type));
    }


    /**
     * Handles a relop Token
     *
     * @param i         current index in string
     * @param currLine  char array representing of the current line
     * @param tokenList list of tokens
     * @param lineNum   current line number of input file
     * @return Index of last character in a token, -1 if error parsing
     */
    private static int handleRelOpToken(int i, char[] currLine, ArrayList<Token> tokenList, int lineNum) {
        // Attempt to index array successfully
        char  first = currLine[i]; 
        if (first == '<' || first == '>' || first == '!' ) {
            // check for following =
            i++;
            char follow = currLine[i];
            String fullLexeme;
            if (follow == '=') {
                i++;
                fullLexeme = Character.toString(first) + Character.toString(follow);
            } else {
                fullLexeme = Character.toString(first);
            }
            tokenList.add(new Token(fullLexeme, globalFileName, lineNum, TokenType.REL_OP));
            return i-1;
        } else if (first == '=' && currLine[i+1] == '=') {
            // Check for ==
            i++;
            i++;
            String fullLexeme = "==";
            tokenList.add(new Token(fullLexeme, globalFileName, lineNum, TokenType.REL_OP));
            return i;
        } else {
            return -1;
        }

    }

    /**
     * Handles number token
     * @param index     current index in string
     * @param currLine  char array representing of the current line
     * @param tokenList list of tokens
     * @param lineNum   current line number of input file
     * @return  Index of last character in a token, -1 if error parsing
     */
    private static int handleNumberToken(int i, char[] currLine, ArrayList<Token> tokenList, int lineNum) {
        //need to go through and replace all checks for token type w/ getTokenType()
        char currChar = currLine[i];
        String currLexeme = "";
        while (currChar >= '0' && currChar <= '9') {
            currLexeme += Character.toString(currChar);
            i++;
            currChar = currLine[i];
        }
        if (currChar == '.') {
            currLexeme += ".";
            currChar = currLine[++i];
            while (currChar >= '0' && currChar <= '9') {
                currLexeme += Character.toString(currChar);
                i++;
                currChar = currLine[i];
            }
            // currLexeme += handleDecimal(i, currLine, tokenList, lineNum);
        }

        tokenList.add(new Token(currLexeme, globalFileName, lineNum, TokenType.NUMBER));

        return i-1;
    }

    /**
     * Handles checking if character is a letter or a digit.
     * @param c character
     * @return true if c is a letter or digit otherwise false
     */
    private static boolean isLetterOrDigit(char c) {
        return (Character.toLowerCase(c) >= 'a' && Character.toLowerCase(c) <= 'z'
                || (c >= '0' && c <= '9'));
    }

    /**
     * Handles getting id/keyword token
     *
     * @param i         current index in string
     * @param currLine  char array representing of the current line
     * @param tokenList list of tokens
     * @param lineNum   current line number of input file
     * @return   Index of last character in a token, -1 if error parsing
     */
    private static int handleIdKeywordToken(int i, char[] currLine,
                                            ArrayList<Token> tokenList, int lineNum) {
        StringBuilder currLexeme = new StringBuilder();
        char currChar;
        int idx;
        for (idx = i; idx < currLine.length; idx++) {
            currChar = currLine[idx];
            if (!isLetterOrDigit(currChar)) {
                break;
            }
            currLexeme.append(currChar);
        }
        tokenList.add(new Token(currLexeme.toString(), globalFileName, lineNum, TokenType.ID_KEYWORD));
        return idx - 1;
    }

    /**
     * Handles getting a full string token
     *
     * @param i         current index in string
     * @param currLine  char array representing of the current line
     * @param tokenList list of tokens
     * @param lineNum   current line number of input file
     * @return Index of last character in a token, -1 if error parsing
     */
    private static int handleStringToken(int i, char[] currLine, ArrayList<Token> tokenList, int lineNum) {
        // Attempt to parse entire string
        StringBuilder string = new StringBuilder("\"");
        try {

            // Get all characters until reach closing quotations
            while (currLine[++i] != '\"')
                string.append(currLine[i]);
            string.append("\"");    // append closing quotations

            // Add to token list
            tokenList.add(new Token(string.toString(), globalFileName, lineNum, TokenType.STRING));
            return i;    // index of last character in the string token
        } catch (IndexOutOfBoundsException e) {
            // ie didn't find closing quotation
            tokenList.add(new Token(string.toString(), globalFileName, lineNum, TokenType.STRING));
            return -1;
        }
    }

    /**
     * Handles getting assign token
     *
     * @param i         current index in string
     * @param currLine  char array representing of the current line
     * @param lineNum   current line number of input file
     * @param tokenList list of tokens
     * @param type      the Token type
     *
     * @return  Index of last character in a token, -1 if error parsing
     */
    private static int handleAssignToken(int i, char[] currLine, int lineNum,
                                         ArrayList<Token> tokenList, TokenType type) {
        // todo inc i to check if Assign turns into relop. If it does then add rel op instead of Assign
        StringBuilder currLexeme = new StringBuilder();
        currLexeme.append(currLine[i]);
        if (i == currLine.length - 1) {
            tokenList.add(new Token(currLexeme.toString(), globalFileName, lineNum, TokenType.ASSIGN));
        } else if (currLine[i + 1] == '=') {
            currLexeme.append(currLine[i + 1]);
            return handleRelOpToken(i, currLine, tokenList, lineNum);
        } else {
            tokenList.add(new Token(currLexeme.toString(), globalFileName, lineNum, TokenType.ASSIGN));
        }
        return i;
    }

    /**
     * Handles getting math op
     *
     * @param symbol    '/', '+', '-', '*' character
     * @param tokenList list of tokens
     * @param lineNum   current line number of input file
     */
    private static void handleMathOp(char symbol, ArrayList<Token> tokenList, int lineNum) {
        tokenList.add(new Token(Character.toString(symbol), globalFileName, lineNum, TokenType.MATH_OP));
    }

    /**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     *
     * @param fileName the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String fileName) {

        // Attempt to read file
        globalFileName = fileName;
        Scanner inputScan;
        try {
            File inputFile = new File(fileName);
            inputScan = new Scanner(inputFile);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("File not found: " + fileName);
            return null;
        }

        // init tokenization variables
        ArrayList<Token> tokenList = new ArrayList<>();
        StringBuilder currTokenString = new StringBuilder();
        int lineNum = 0;    // Needed for Token
        String currLine;
        // Parse file until EOF
        while (inputScan.hasNextLine()) {
            //line currently processing, allows you to look ahead because you can access the current line from token handling funcs
            currLine = inputScan.nextLine();
            lineNum++;

            // Parse each character of the current line
            for (int i = 0; i < currLine.length(); i++) {
                //should be the for loop actually be incrementing?
                //the functions *should* be doing the increases, increment might cause char skipping... -zoe
                char currChar = currLine.charAt(i);
                // skip if whitespace
                if (currChar == ' ' || currChar == '\t')
                    continue;

                // if a comment, trash rest of line
                if (currChar == '#')
                    break;

                // Check if simple token only if current token string is 1 character
                TokenType tokenType = getTokenType(currChar);

                //call your token handling function in here
                switch (tokenType) {
                    case MATH_OP -> handleMathOp(currChar, tokenList, lineNum);
                    case COMMA ->
                            handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.COMMA);
                    case R_BRACKET ->
                            handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.R_BRACKET);
                    case L_BRACKET ->
                            handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.L_BRACKET);
                    case R_BRACE ->
                            handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.R_BRACE);
                    case L_BRACE ->
                            handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.L_BRACE);
                    case ASSIGN ->
                            i = handleAssignToken(i, currLine.toCharArray(), lineNum, tokenList, TokenType.ASSIGN);
                    case REL_OP -> i = handleRelOpToken(i, currLine.toCharArray(), tokenList, lineNum);
                    case SEMICOLON ->
                            handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.SEMICOLON);
                    case NUMBER -> i = handleNumberToken(i, currLine.toCharArray(), tokenList, lineNum);
                    case ID_KEYWORD -> i = handleIdKeywordToken(i, currLine.toCharArray(), tokenList, lineNum);
                    case COLON ->
                            handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.COLON);
                    case STRING -> i = handleStringToken(i, currLine.toCharArray(), tokenList, lineNum);
                }
                if (i == -1) {
                    System.out.println("Syntax Error: \nInvalid token \"" + tokenList.get(tokenList.size() - 1).getToken() + "\" \n" + fileName.split("/")[1] + ":" + lineNum);
                    return null;
                }
            }
        }

        // return list of tokens found
        return tokenList;
    }

}
