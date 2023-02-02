
package provided;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Derek Garcia
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JottTokenizer {
	
	private String currLine = "";


	private static TokenType getTokenType(char currChar) {
		
		TokenType type = switch (currChar) {
			case ',' -> return TokenType.COMMA;
			case ']' -> return TokenType.L_BRACKET;
			case '[' -> return TokenType.R_BRACKET;
			case '}' -> return TokenType.L_BRACE;
			case '{' -> return TokenType.R_BRACE;
			case ';' -> return TokenType.SEMICOLON;
			case ':' -> return TokenType.COLON;
			// All math tokens are 'mathOp' tokens
			case '/', '+', '-', '*' -> return TokenType.MATH_OP;
			default -> null;
		};
		
		if (type == null) {
			if (currChar == '=') {
				return TokenType.ASSIGN;
			} else if (currChar == '<' || currChar == '>') {
				return TokenType,REL_OP;
			} else if (currChar == '.') {
				return TokenType.NUMBER;
			} else if (currChar >= '0' && currChar <= '9') {
				return TokenType.NUMBER;
			} else if (Character.toLowerCase(currChar) >= 'a'
						&& Character.toLowerCase(currChar) <= 'z') {
				return TokenType.ID_KEYWORD;
			} else if (currChar == '!') {
				return TokenType.REL_OP;
			} else if (currChar == '"') {
				return TokenType.STRING;
			} else {
				// throw error
			}
		}
	}
	
	private static void handleComment() { }

	private static int handleAssignmentToken() { return 0; }

	private static int handleRelOpToken() { return 0;	}

	private static int handleDecimal() { return 0; }

	private static int handleNumberToken() { return 0; }

	private static int handleIdKeywordToken() { return 0; }

	private static int handleStringToken() { return 0; }

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param fileName the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String fileName){

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
		StringBuilder currTokenString = new StringBuilder();
		int lineNum = 0;	// Needed for Token

		// Parse file until EOF
		while (inputScan.hasNextLine()) {
			currLine = inputScan.nextLine();
			lineNum++;

			// Parse each character of the current line
			for (int i = 0; i < currLine.length(); i++) {
				char currChar = currLine.charAt(i);
				currTokenString.append(currChar);
				if (currChar == '#') {
					break;
				}

				// Check if simple token only if current token string is 1 character
				TokenType tokenType = getTokenType(currChar);

				switch(tokenType) {
					// Call method based on type	
					case TokenType.STRING:
						i = handleStringToken(i);
						break;
				}
			}
		}

		// return list of tokens found
		return tokenList;
	}
