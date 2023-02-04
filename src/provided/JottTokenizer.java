package provided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for tokenizing Jott code.
 *
 * @author Derek Garcia
 **/

public class JottTokenizer {

	/**
	 * Test to see if character is a single character token
	 *
	 * @param c character to test
	 * @return TokenType if found, null otherwise
	 */
	private static TokenType getSingleCharToken(char c){
		return switch (c) {
			case ',' ->  TokenType.COMMA;
			case ']' ->  TokenType.L_BRACKET;
			case '[' ->  TokenType.R_BRACKET;
			case '}' ->  TokenType.L_BRACE;
			case '{' ->  TokenType.R_BRACE;
			case ';' ->  TokenType.SEMICOLON;
			case ':' ->  TokenType.COLON;
			// All math tokens are 'mathOp' tokens
			case '/', '+', '-', '*' ->  TokenType.MATH_OP;
			default -> null;
		};
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
		StringBuilder tokenString = new StringBuilder();
		int lineNum = 0;    // Needed for Token

		// Parse file until EOF
		while (inputScan.hasNextLine()) {
			String currLine = inputScan.nextLine();
			lineNum++;

			// Parse each character of the current line
			for (int i = 0; i < currLine.length(); i++) {
				char currChar = currLine.charAt(i);
				// skip if whitespace
				if(currChar == ' ')
					continue;

				// if a comment, trash rest of line
				if(currChar == '#')
					break;

				// else start building token
				tokenString.append(currChar);

				// if single char, test if it's a token
				if(tokenString.length() == 1){
					TokenType type = getSingleCharToken(currChar);
					// if it is a token, add to list and continue
					if(type != null){
						tokenList.add(new Token(tokenString.toString(), fileName, lineNum, type));
						tokenString = new StringBuilder();	// reset token sting
						continue;
					}
				}






			}
		}

		// return list of tokens found
		return tokenList;
	}

}
