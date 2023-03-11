package provided;



public class ParsingError extends Error {

  private final String errorType;
  private final String expected;
  private final Token actual;

  /**
   * ParsingError is used to print out an error message to std error.
   * @param expected: What was expected at this stage of parsing.
   * @param actual: The token at the current stage was received.
   */
  public ParsingError(String errorType, String expected, Token actual) {
    this.errorType = errorType;
    this.expected = expected;
    this.actual = actual;
  }

  @Override
  public String toString(){
    return this.errorType + ":\n"
            + "Error with parsing file, expected: " + this.expected + " received: " + this.actual.getToken() + "\n"
            + this.actual.getFilename() + ":" + this.actual.getLineNum();
  }

  
}
