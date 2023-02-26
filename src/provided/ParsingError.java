package provided;

public class ParsingError {

  /**
   * ParsingError is used to print out an error message to std error.
   * @param expected: What was expected at this stage of parsing.
   * @param actual: What was received.
   */
  public ParsingError(String expected, String actual) {
    String errorMessage = "Syntax Error:\n"
        + "Error with parsing file, expected: " + expected + " received: " + actual;

    System.err.println(errorMessage);
  }

}
