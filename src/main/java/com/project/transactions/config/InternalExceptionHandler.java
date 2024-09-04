package com.project.transactions.config;

import com.project.transactions.domain.ErrorResponse;
import com.project.transactions.domain.exception.BusinessException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.webjars.NotFoundException;

@ControllerAdvice
public class InternalExceptionHandler {

  @ExceptionHandler({BusinessException.class})
  public ResponseEntity<Object> handleBusinessException(Exception ex) {
    return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
  }

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<Object> handleNotFoundException(Exception ex) {
    return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    String errorMessage =
        Optional.ofNullable(ex.getBindingResult().getFieldError())
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .orElse("Invalid request content.");
    return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException ex) {
    return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<Object> handleMethodValidationException(
      HandlerMethodValidationException ex) {
    String errorMessage = ex.getMessage();
    Object[] detailMessageArguments = ex.getDetailMessageArguments();
    if (detailMessageArguments != null && detailMessageArguments.length > 0) {
      errorMessage = Arrays.stream(detailMessageArguments).findFirst().map(Object::toString).get();
    }
    return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
  }

  private ResponseEntity<Object> buildErrorResponse(HttpStatus httpStatus, String message) {
    ErrorResponse errorResponse =
        new ErrorResponse(
            LocalDateTime.now(), httpStatus.value(), httpStatus.getReasonPhrase(), message);
    return new ResponseEntity<>(errorResponse, httpStatus);
  }
}
