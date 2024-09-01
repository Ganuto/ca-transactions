package com.project.transactions.config;

import com.project.transactions.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class InternalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String TIMESTAMP = "timestamp";
    private static final String HTTP_CODE = "httpCode";
    private static final String HTTP_STATUS = "httpStatus";
    private static final String ERROR_MESSAGE = "errorMessage";

    public InternalExceptionHandler() {
        super();
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Map<String, Object>> handleBusinessException(Exception ex) {
        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNotFoundException(Exception ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus httpStatus, String message) {
        Map<String, Object> errorInfo = buildErrorInfo(httpStatus, message);
        return new ResponseEntity<>(errorInfo, httpStatus);
    }

    private Map<String, Object> buildErrorInfo(HttpStatus httpStatus, String message) {
        Map<String, Object> errorInfo = new LinkedHashMap<>();
        errorInfo.put(TIMESTAMP, LocalDateTime.now());
        errorInfo.put(HTTP_CODE, httpStatus.value());
        errorInfo.put(HTTP_STATUS, httpStatus.getReasonPhrase());
        errorInfo.put(ERROR_MESSAGE, message);
        return errorInfo;
    }
}
