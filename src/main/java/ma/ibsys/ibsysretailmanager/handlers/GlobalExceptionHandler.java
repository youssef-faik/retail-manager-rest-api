package ma.ibsys.ibsysretailmanager.handlers;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
    log.error("Entity not found error: {}", ex.getMessage());
    return ErrorResponse.builder()
        .httpStatus(HttpStatus.BAD_REQUEST)
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    log.error("Validation error: {}", ex.getMessage());
    BindingResult bindingResult = ex.getBindingResult();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();

    List<String> errors = new ArrayList<>();
    for (FieldError fieldError : fieldErrors) {
      errors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
    }

    return ErrorResponse.builder()
        .httpStatus(HttpStatus.BAD_REQUEST)
        .message(errors.toString())
        .timestamp(LocalDateTime.now())
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleException(Exception ex) {
    log.error("Unexpected error: {}", ex.getMessage());
    return ErrorResponse.builder()
        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
  }
}