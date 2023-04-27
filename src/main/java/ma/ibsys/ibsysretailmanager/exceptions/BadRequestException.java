package ma.ibsys.ibsysretailmanager.exceptions;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String message)  {
    super(message);
  }
}
