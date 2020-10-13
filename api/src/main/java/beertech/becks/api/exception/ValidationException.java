package beertech.becks.api.exception;

public class ValidationException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String code;

  public ValidationException(String code, String message) {
    super(message);
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
