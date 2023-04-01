package provided;



public class ParsingError extends Error {

  private final String errorType;
  private final String expected;
  private final Token actual;
  private final String message;
  private final String fileName;
  private final int lineNum;
  
  

  /**
   * ParsingError is used to print out an error message to std error.
   * @param expected: What was expected at this stage of parsing.
   * @param actual: The token at the current stage was received.
   */
  public ParsingError(String errorType, String expected, Token actual) {
    this.errorType = errorType;
    this.expected = expected;
    this.actual = actual;
    this.fileName = actual.getFilename();
    this.lineNum = actual.getLineNum();
    this.message = null;
  }
  
  public ParsingError(String errorType, String message, String fileName, int lineNum) {
    this.errorType = errorType;
    this.message = message;
    this.actual = null;
    this.expected = null;
    this.fileName = fileName;
    this.lineNum = lineNum;
  }

  @Override
  public String toString(){
    if (this.message != null) {
      return this.errorType + ":\n"
              + "Error with validating tree: " + this.message + "\n"
              + this.fileName + ":" + this.lineNum;
    }
    return this.errorType + ":\n"
            + "Error with parsing file, expected: " + this.expected + " received: " + this.actual.getToken() + "\n"
            + this.fileName + ":" + this.lineNum;
  }

  
}
