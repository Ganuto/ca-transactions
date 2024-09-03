package com.project.transactions.config;

import com.project.transactions.domain.ErrorResponse;
import com.project.transactions.domain.exception.BusinessException;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

@ControllerAdvice
public class InternalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({BusinessException.class})
  public ResponseEntity handleBusinessException(Exception ex) {
    return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
  }

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity handleNotFoundException(Exception ex) {
    return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//    return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//    return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
//  }

  private ResponseEntity buildErrorResponse(HttpStatus httpStatus, String message) {
    ErrorResponse errorResponse =
        new ErrorResponse(
            LocalDateTime.now(), httpStatus.value(), httpStatus.getReasonPhrase(), message);
    return new ResponseEntity(errorResponse, httpStatus);
  }
}
