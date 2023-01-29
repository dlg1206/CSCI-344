package provided;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JottTokenizer {

	private static void handleComment() { }

	private static int addCommaToken() { return 0; }

	private static int addLBracketToken() { return 0; }

	private static int addRBracketToken() { return 0; }

	private static int addLBraceToken() { return 0; }

	private static int addRBraceToken() { return 0; }

	private static int addSemiColonToken() { return 0; }

	private static int addColonToken() { return 0; }

	private static int addMathOpToken() { return 0; }

	private static int handleAssignmentToken() { return 0; }

	private static int handleRelOpToken() { return 0;	}

	private static int handleDecimal() { return 0; }

	private static int handleNumberToken() { return 0; }

	private static int handleIdKeywordToken() { return 0; }

	private static int handleStringToken() { return 0;	}

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){

		// Attempt to read file
		Scanner inputScan;
		try {
			File inputFile = new File(filename);
			inputScan = new Scanner(inputFile);
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("File not found: " + filename);
			return null;
		}

		// init tokenization variables
		ArrayList<Token> tokenList = new ArrayList<>();
		StringBuilder currToken = new StringBuilder();

		// Parse file until EOF
		String currLine;
		while (inputScan.hasNextLine()) {
			currLine = inputScan.nextLine();

			// Parse each character of the current line
			for (int i = 0; i < currLine.length(); i++) {
				char currChar = currLine.charAt(i);
				currToken.append(currChar);		// todo Append immediately? ie a1 -> a1 token instead of a and 1
				if (currChar == '#') {
					handleComment();
				} else if (currChar == ',') {
					i = addCommaToken();
				} else if (currChar == ']') {
					i = addRBracketToken();
				} else if (currChar == '[') {
					i = addLBracketToken();
				} else if (currChar == '}') {
					i = addRBraceToken();
				} else if (currChar == '{') {
					i = addLBraceToken();
				} else if (currChar == '=') {
					i = handleAssignmentToken();
				} else if (currChar == '<' || currChar == '>') {
					i = handleRelOpToken();
				} else if (currChar == '/' || currChar == '+'
								|| currChar == '-' ||currChar == '*') {
					i = addMathOpToken();
				} else if (currChar == ';') {
					i = addSemiColonToken();
				} else if (currChar == '.') {
					i = handleDecimal();
				} else if (currChar >= '0' && currChar <= '9') {
					i = handleNumberToken();
				} else if (Character.toLowerCase(currChar) >= 'a'
							&& Character.toLowerCase(currChar) <= 'z') {
				  i = handleIdKeywordToken();
				} else if (currChar == ':') {
					i = addColonToken();
				} else if (currChar == '!') {
					i = handleRelOpToken();
				} else if (currChar == '"') {
					i = handleStringToken();
				}
			}
		}

		// return list of tokens found
		return tokenList;
	}
}