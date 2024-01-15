package com.jobinow.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Custom exception class for handling Stripe payment-related exceptions.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 * @version 1.0
 */
@Getter
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class StripeCustomException extends ResourceException {

    /**
     * Default error message for StripeCustomException.
     */
    private static final String DEFAULT_MESSAGE = "An error occurred during the Stripe payment process.";

    /**
     * Constructor for StripeCustomException with a custom message.
     *
     * @param message The custom error message.
     */
    public StripeCustomException(String message) {
        super(message);
    }

    /**
     * Constructor for StripeCustomException with the default message.
     */
    public StripeCustomException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Exception handler method for handling StripeCustomException.
     * Converts the exception into an ApiErrorFactory and returns it in a ResponseEntity.
     *
     * @param ex The StripeCustomException to handle.
     * @return ResponseEntity with ApiErrorFactory representing the error.
     */
    @ExceptionHandler(StripeCustomException.class)
    public ResponseEntity<ApiErrorFactory> handleStripeCustomException(StripeCustomException ex) {
        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}