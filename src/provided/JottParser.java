package provided;

/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author
 */

import java.util.ArrayList;
import provided.*;
import provided.variables.Program;

public class JottParser {

    /**
     * Parses an ArrayList of Jotton tokens into a Jott Parse Tree.
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     *         or null upon an error in parsing.
     */
    public static JottTree parse(ArrayList<Token> tokens){
        // Attempt to parse the jott tree
        try {
            return Program.parseProgram(tokens);
        } catch (ParsingError e){
            // Parse error, print message and return null
            System.err.println(e.toString());
            return null;
        } catch (IndexOutOfBoundsException e){
            // debug output
            e.printStackTrace();
            return null;
        } catch (Exception e){
            System.err.println("Unknown Issue :(");
            e.printStackTrace();
            return null;
        }
    }
}
