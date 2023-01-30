package provided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

import java.util.ArrayList;

public class JottTokenizer {

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename) {
		ArrayList<Token> tokenList;
		//taking in the file and parsing it into tokens
		File file = new File(filename);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("\n\nFile not found");
		}
		String currToken = ""; //the string we're adding chars to-- must be outside the while loop b/c token can be mult lines
		while (sc.hasNextLine()) {
			String currLine = sc.next();
			for (int i = 0; i < currLine.length(); i++) {
				currToken.concat(String.valueOf(currLine.charAt(i)));
				//need to process the string here to decide what to do w it
				//should the token string be added to till it's accepting then made into a token
				//OR should there be a token that we consistently add to until moving on?
				//I think it depends on whether Nick's tokenizer functions take a string or token as a param
				//probably easier to have it be a token while processing bc it can store a token type
			}
			System.out.println(sc.nextLine());
		}
		sc.close();

		return null;
	}
}