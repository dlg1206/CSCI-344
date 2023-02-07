
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

	private static String globalFileName;


	private static TokenType getTokenType(char currChar) {

		TokenType type = switch (currChar) {
			case ',' -> TokenType.COMMA;
			case ']' -> TokenType.L_BRACKET;
			case '[' -> TokenType.R_BRACKET;
			case '}' -> TokenType.L_BRACE;
			case '{' -> TokenType.R_BRACE;
			case ';' -> TokenType.SEMICOLON;
			case ':' -> TokenType.COLON;
			// All math tokens are 'mathOp' tokens
			case '/', '+', '-', '*' -> TokenType.MATH_OP;
			default -> null;
		};
		if (type == null) {
			if (currChar == '=') {
				type = TokenType.ASSIGN;
			} else if (currChar == '<' || currChar == '>') {
				type = TokenType.REL_OP;
			} else if (currChar == '.') {
				type = TokenType.NUMBER;
			} else if (currChar >= '0' && currChar <= '9') {
				type = TokenType.NUMBER;
			} else if (Character.toLowerCase(currChar) >= 'a'
					&& Character.toLowerCase(currChar) <= 'z') {
				type = TokenType.ID_KEYWORD;
			} else if (currChar == '!') {
				type = TokenType.REL_OP;
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
	//gl lads
	private static void handleComment() {
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
	private static int getRelOPEndIndex(String line, int i) {
		// todo follow number DFA to find the index where the RelOP token ends
		return i;
	}

	private static int handleDecimal() {
		return 0;
	}

	private static int handleNumberToken() {
		return 0;
	}

	private static boolean isLetterOrDigit(char c) {
		return (Character.toLowerCase(c) >= 'a' && Character.toLowerCase(c) >= 'z'
				|| (c >= '0' && c <= '9'));
	}


	private static int handleIdKeywordToken(int i, char[] currLine,
																					ArrayList<Token> tokenList, int lineNum) {
		StringBuilder currLexeme = new StringBuilder();
		char currChar = currLine[i];
		while (isLetterOrDigit(currChar)) {
			currLexeme.append(currChar);
			i++;
			currChar = currLine[i];
		}
		tokenList.add(new Token(currLexeme.toString(), globalFileName, lineNum, TokenType.ID_KEYWORD));
		return i;
	}

	private static int handleStringToken() {
		return 0;
	}

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
				char currChar = currLine.charAt(i);
				// skip if whitespace
				if(currChar == ' ')
					continue;

				// if a comment, trash rest of line
				if(currChar == '#')
					break;

				// Test if single char token
//				TokenType type = getSingleCharToken(currChar);
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

				// Check if simple token only if current token string is 1 character
				TokenType tokenType = getTokenType(currChar);

				//call your token handling function in here
				switch (tokenType) {
					// Call method based on type
					case MATH_OP -> handleMathOp(currChar, tokenList, lineNum);
					case ID_KEYWORD -> i = handleIdKeywordToken(i, currLine.toCharArray(), tokenList, lineNum);
					case STRING -> i = handleStringToken();

				}

				// if digit or can lead to digit, get tokenString
				if(Character.isDigit(currChar) || currChar == '.'){
					int endIndex = getNumberTokenEndIndex(currLine, i);
					// -1 indicates error
					if(endIndex == -1)
						return null;
					tokenList.add(new Token(currLine.substring(i, endIndex), fileName, lineNum, TokenType.NUMBER));
					i = endIndex;	// update new location
					continue;
				}

				// if letter, get tokenString
				if(Character.isLetter(currChar)){
					int endIndex = getIDKeywordTokenEndIndex(currLine, i);
					// -1 indicates error
					if(endIndex == -1)
						return null;
					tokenList.add(new Token(currLine.substring(i, endIndex), fileName, lineNum, TokenType.ID_KEYWORD));
					i = endIndex;	// update new location
					continue;
				}

				// if relop, get token string
				if(currChar == '=' || currChar == '>' || currChar == '<' || currChar == '!'){
					int endIndex = getRelOPEndIndex(currLine, i);
					// -1 indicates error
					if(endIndex == -1)
						return null;
					tokenList.add(new Token(currLine.substring(i, endIndex), fileName, lineNum, TokenType.REL_OP));
					i = endIndex;	// update new location
				}

			}
		}

		// return list of tokens found
		return tokenList;
	}

}
