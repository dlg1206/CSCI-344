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

	// line: current line, i: current index. Return -1 to indicate err
	private static int getNumberTokenEndIndex(String line, int i){
		// todo follow number DFA to find the index where the number token ends
		return i;
	}

	// line: current line, i: current index. Return -1 to indicate err
	private static int getIDKeywordTokenEndIndex(String line, int i){
		// todo follow number DFA to find the index where the IDKeyword token ends
		return i;
	}

	// line: current line, i: current index. Return -1 to indicate err
	private static int getRelOPEndIndex(String line, int i){
		// todo follow number DFA to find the index where the RelOP token ends
		return i;
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

				// Test if single char token
				TokenType type = getSingleCharToken(currChar);
				// if it is a token, add to list and continue
				if(type != null){
					tokenList.add(new Token(String.valueOf(currChar), fileName, lineNum, type));
					continue;
				}

				// test for assign
				// todo can probably merge into one if
				if(currChar == '='){
					// if at EOL and can't check next char
					if(i == currLine.length() - 1){
						tokenList.add(new Token(String.valueOf(currChar), fileName, lineNum, TokenType.ASSIGN));
						continue;
					}

					// check next char isn't '='
					if(currLine.charAt(i + 1) != '='){
						tokenList.add(new Token(String.valueOf(currChar), fileName, lineNum, TokenType.ASSIGN));
						continue;
					}

				}

				// test for string
				if(currChar == '"'){

					try{
						StringBuilder str = new StringBuilder("\"");
						while (currLine.charAt(i++) != '"')
							str.append(currLine.charAt(i));
						str.append("\"");	// close string
						tokenList.add(new Token(str.toString(), fileName, lineNum, TokenType.STRING));
						continue;
					} catch (IndexOutOfBoundsException e){
						// string was never closed
						return null;
					}

				}


				// if digit or can lead to digit, get tokenString
				if(Character.isDigit(currChar) || currChar == '.'){
					int endIndex = getNumberTokenEndIndex(currLine, i);
					// -1 indicates error
					if(endIndex == -1)
						return null;
					tokenList.add(new Token(currLine.substring(i, endIndex), fileName, lineNum, TokenType.NUMBER));
					continue;
				}

				// if letter, get tokenString
				if(Character.isLetter(currChar)){
					int endIndex = getIDKeywordTokenEndIndex(currLine, i);
					// -1 indicates error
					if(endIndex == -1)
						return null;
					tokenList.add(new Token(currLine.substring(i, endIndex), fileName, lineNum, TokenType.ID_KEYWORD));
					continue;
				}

			}
		}

		// return list of tokens found
		return tokenList;
	}

}
