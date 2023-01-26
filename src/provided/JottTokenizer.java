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
	// Receives a char and will return a TokenType based on Which type it falls under. We only do this
	// If curr TokenType == Null
	private TokenType getTokenType(char currChar) { return null;}

	private void handleComment() { }

	private void addCommaToken() { }

	private void addLBracketToken() { }

	private void addRBracketToken() { }

	private void addLBraceToken() { }

	private void addRBraceToken() { }

	private void addSemiColonToken() { }

	private void addColonToken() { }

	private void addMathOpToken() { }

	private void handleAssignmentToken() { }

	private void handleRelOpToken() {	}

	private void handleNumberToken() { }

	private void handleIdKeywordToken() {	}

	private void handleStringToken() {	}

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){
			Scanner inputScan;
			try {
				File inputFile = new File(filename);
				inputScan = new Scanner(inputFile);
			} catch (FileNotFoundException fileNotFoundException) {
				System.err.println(fileNotFoundException);
				return null;
			}

			ArrayList<Token> tokenList = new ArrayList<>();
			String currToken = "";
			TokenType currTokenType = null;
			String currLine;
			while (inputScan.hasNextLine()) {
				currLine = inputScan.nextLine();
				// Loop through chars of line
			}



			return null;
	}
}