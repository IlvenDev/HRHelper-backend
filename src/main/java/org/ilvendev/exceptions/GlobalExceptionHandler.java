package org.ilvendev.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.exceptions.resource_exceptions.DuplicateResourceException;
import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.exceptions.validation_exceptions.BusinessValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Structure for error responses
    @Getter
    @AllArgsConstructor
    public static class ApiError {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private Map<String, String> details;
    }

    // Helper method to build error responses
    private ApiError buildApiError(
            HttpStatus status,
            Exception ex,
            WebRequest request,
            Map<String, String> details) {

        String path = ((ServletWebRequest) request).getRequest().getRequestURI();

        return new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                path,
                details
        );
    }

    // Handle resource not found exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildApiError(HttpStatus.NOT_FOUND, ex, request, null));
    }

    // Handle duplicate resource exceptions
    @ExceptionHandler(value = DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicateResourceException(
            DuplicateResourceException ex, WebRequest request) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(buildApiError(HttpStatus.CONFLICT, ex, request, null));
    }

    // Handle business validation exceptions
    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<ApiError> handleBusinessValidationException(
            BusinessValidationException ex, WebRequest request) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(buildApiError(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        ex,
                        request,
                        ex.getErrors()
                ));
    }

    // Handle validation exceptions from @Valid annotation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                details.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildApiError(
                        HttpStatus.BAD_REQUEST,
                        new Exception("Validation failed"),
                        request,
                        details
                ));
    }

    // Catch-all for unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaughtException(
            Exception ex, WebRequest request) {

        // Log the unexpected exception
        log.error("Unexpected error occurred", ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildApiError(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        new Exception("An unexpected error occurred. Please contact support."),
                        request,
                        null
                ));
    }
}