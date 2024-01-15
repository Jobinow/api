package com.jobinow.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Custom exception class for handling invalid payment scenarios.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 * @version 1.0
 */
@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidPaymentException extends ResourceException {

    /**
     * Default error message for InvalidPaymentException.
     */
    private static final String DEFAULT_MESSAGE = "Invalid payment details provided.";

    /**
     * Constructor for InvalidPaymentException with a custom message.
     *
     * @param message The custom error message.
     */
    public InvalidPaymentException(String message) {
        super(message);
    }

    /**
     * Constructor for InvalidPaymentException with the default message.
     */
    public InvalidPaymentException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Exception handler method for handling InvalidPaymentException.
     * Converts the exception into an ApiErrorFactory and returns it in a ResponseEntity.
     *
     * @param ex The InvalidPaymentException to handle.
     * @return ResponseEntity with ApiErrorFactory representing the error.
     */
    @ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<ApiErrorFactory> handleInvalidPaymentException(InvalidPaymentException ex) {
        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.BAD_REQUEST,
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}