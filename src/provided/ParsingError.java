package provided;

import provided.Token;

public class ParsingError {

  /**
   * ParsingError is used to print out an error message to std error.
   * @param expected: What was expected at this stage of parsing.
   * @param actual: The token at the current stage was received.
   */
  public ParsingError(String errorType, String expected, Token actual) {
    String errorMessage = errorType + ":\n"
        + "Error with parsing file, expected: " + expected + " received: " + actual.getToken() + "\n"
        + actual.getFilename() + ":" + actual.getLineNum();
    
    System.err.println(errorMessage);
  }
}
