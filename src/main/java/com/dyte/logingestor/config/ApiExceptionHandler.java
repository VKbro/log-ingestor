package com.dyte.logingestor.config;

import com.dyte.logingestor.response.EntityException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the API.
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException and returns a ResponseEntity with appropriate error messages.
     *
     * @param ex      The exception that was thrown.
     * @param headers The headers for the response.
     * @param status  The HTTP status for the response.
     * @param request The current web request.
     * @return ResponseEntity containing error details.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        // Prepare error messages and suggestions
        Map<String, String> errorMessages = new HashMap<>();
        errorMessages.put("suggestion 1", "You may be missing some keys! Check the API documentation.");
        errorMessages.put("suggestion 2", "You may be violating minimum required terms to find something!");

        // Create EntityException object with HttpStatus and error messages
        var errorObject = new EntityException(HttpStatus.BAD_REQUEST, errorMessages);

        // Handle the exception and return a ResponseEntity with error details
        return handleExceptionInternal(
                ex,
                errorObject,
                headers,
                errorObject.getHttpStatus(),
                request
        );
    }
}