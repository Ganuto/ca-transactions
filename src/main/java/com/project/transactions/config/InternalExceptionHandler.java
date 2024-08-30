package com.project.transactions.config;

import com.project.transactions.domain.BusinessException;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @ExceptionHandler({BusinessException.class, BadRequestException.class})
    public ResponseEntity<Map<String, Object>> handleBusinessException(Exception ex) {
        return buildErrorResponse(HttpStatus.PRECONDITION_FAILED, ex.getMessage());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(Exception ex) {
        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    // TODO: Adjust the handler below and add more handlers
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Map<String, Object>> handleNullPointerException(Exception ex) {
        return buildErrorResponse(HttpStatus.PRECONDITION_FAILED, ex.getMessage());
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
