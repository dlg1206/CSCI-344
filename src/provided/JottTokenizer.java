
package provided;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Derek Garcia, Zoe Wheatcroft, Celeste Gambardella
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
	//gl lad

	private static void handleSingleCharToken(String symbol, int lineNum,
																					 ArrayList<Token> tokenList, TokenType type) {
		tokenList.add(new Token(symbol, globalFileName, lineNum, type));
	}

	// line: current line, i: current index. Return -1 to indicate err
	private static int getNumberTokenEndIndex(int i, char[] currLine){
		// todo follow number DFA to find the index where the number token ends
		return i;
	}

	// line: current line, i: current index. Return -1 to indicate err
	private static int handleRelOpToken(int i, char[] currLine, ArrayList<Token> tokenList, int lineNum) {
		int currChar = currLine[i];
		StringBuilder currLexeme = new StringBuilder();
//		todo Inc i and check the next char to find out what type/symbol the rel op will be
		// if relop, get token string
//		if(currChar == '=' || currChar == '>' || currChar == '<' || currChar == '!'){
//			int endIndex = getRelOPEndIndex(currLine, i);
//			// -1 indicates error
//			if(endIndex == -1)
//				return null;
//			tokenList.add(new Token(currLine.substring(i, endIndex), fileName, lineNum, TokenType.REL_OP));
//			i = endIndex;	// update new location
//		}
		return i;
	}
// May not need depending on if
	private static int handleDecimal(int index, char[] currLine, ArrayList<Token> tokenList, int lineNum) {
		//need to go through and replace all checks for token type w/ getTokenType()
		int i  = index;
		String tokString = "";
		if(i == currLine.length - 1){
			try {
				throw new Exception("invalid token");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(!((int)currLine[i+1] > 37 && (int)currLine[i+1] < 58)){
			//if the next char is not a digit
			try {
				throw new Exception("invalid token");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			while(i < currLine.length && (int)currLine[i] > 37 && (int)currLine[i] < 58){
				//while we're still getting a number
				tokString = new String(tokString + currLine[i]);
				i += 1;
			}
		}
		tokenList.add(new Token(tokString, globalFileName, lineNum, TokenType.NUMBER));
		return i;

	}

	private static int handleNumberToken(int index, char[] currLine, ArrayList<Token> tokenList, int lineNum) {
		//need to go through and replace all checks for token type w/ getTokenType()
		char currChar = currLine[index];
		String tokString = "";
		int i = index; //current index
		Boolean decimalFlag = false;
		if(currChar == '.'){
			return handleDecimal(index, currLine, tokenList, lineNum);
		}
		else{
			//know that the first char was a digit
			while(i < currLine.length && (currLine[i] == '.' || ((int)currLine[i] > 37 && (int)currLine[i] < 58))){
				if(currLine[i] == '.'){
					if(!decimalFlag){
						//haven't got a decimal before, means token is still valid
						decimalFlag = true;
					}
					else{
						//got a decimal before, token is now done
						tokenList.add(new Token(tokString, globalFileName, lineNum, TokenType.NUMBER));
						return i;
					}
				}
				tokString = new String(tokString + currLine[i]);
				i+= 1;
			}
			tokenList.add(new Token(tokString, globalFileName, lineNum, TokenType.NUMBER));
			return i;
		}
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

	private static int handleStringToken(int i, char[] currLine, ArrayList<Token> tokenList, int lineNum) {

		return i;
	}

	private static int handleAssignToken(int i, char[] currLine, int lineNum,
															ArrayList<Token> tokenList, TokenType type) {
		// todo inc i to check if Assign turns into relop. If it does then add rel op instead of Assign

		return i;
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
				//should be the for loop actually be incrementing?
				//the functions *should* be doing the increases, increment might cause char skipping... -zoe
				char currChar = currLine.charAt(i);
				// skip if whitespace
				if(currChar == ' ')
					continue;

				// if a comment, trash rest of line
				if(currChar == '#')
					break;

				// Check if simple token only if current token string is 1 character
				TokenType tokenType = getTokenType(currChar);

				//call your token handling function in here
				switch (tokenType) {
					case MATH_OP -> handleMathOp(currChar, tokenList, lineNum);
					case COMMA -> handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.COMMA);
					case R_BRACKET -> handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.R_BRACKET);
					case L_BRACKET -> handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.L_BRACKET);
					case R_BRACE -> handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.R_BRACE);
					case L_BRACE -> handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.L_BRACE);
					case ASSIGN -> handleAssignToken(i, currLine.toCharArray(), lineNum, tokenList, TokenType.ASSIGN);
					case REL_OP -> i = handleRelOpToken(i, currLine.toCharArray(), tokenList, lineNum);
					case SEMICOLON -> handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.SEMICOLON);
					case NUMBER -> i = handleNumberToken(i, currLine.toCharArray(), tokenList, lineNum);
					case ID_KEYWORD -> i = handleIdKeywordToken(i, currLine.toCharArray(), tokenList, lineNum);
					case COLON -> handleSingleCharToken(Character.toString(currChar), lineNum, tokenList, TokenType.COLON);
					case STRING -> handleStringToken(i, currLine.toCharArray(), tokenList, lineNum);
				}
			}
		}

		// return list of tokens found
		return tokenList;
	}

}
