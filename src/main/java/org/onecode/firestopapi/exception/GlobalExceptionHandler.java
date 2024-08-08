package org.onecode.firestopapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.onecode.firestopapi.util.ApiResponse;
import org.onecode.firestopapi.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(HttpServletRequest request, Exception ex) {
        List<String> errors = Arrays.asList(ex.getMessage());
        ApiResponse<Void> response = ResponseUtil.error(errors, "An error occurred", 1000, request.getRequestURI()); // General error
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ApiResponse<Void>> handleMissingPathVariableException(HttpServletRequest request, MissingPathVariableException ex) {
        List<String> errors = Arrays.asList(ex.getMessage());
        ApiResponse<Void> response = ResponseUtil.error(errors, "Missing path variable", 1001, request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(HttpServletRequest request, ValidationException ex) {
        List<String> errors = Arrays.asList(ex.getMessage());
        ApiResponse<Void> response = ResponseUtil.error(errors, "Validation error", 1002, request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}