package com.jobinow.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Exception class for situations where a user is not allowed to create more offers.
 * This exception is typically thrown when a user, such as a recruiter, has reached the maximum allowed number of job offers.
 *
 * @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 * @version 1.0
 */
@Getter
@ResponseStatus(code = HttpStatus.PAYMENT_REQUIRED)
public class NotAllowedToCreateOfferException extends RuntimeException {

    /**
     * Default error message for NotAllowedToCreateOfferException.
     */
    private static final String DEFAULT_MESSAGE = "Not allowed to create more offers.";

    /**
     * Constructor for NotAllowedToCreateOfferException with a custom message.
     *
     * @param message The custom error message.
     */
    public NotAllowedToCreateOfferException(String message) {
        super(message);
    }

    /**
     * Constructor for NotAllowedToCreateOfferException with the default message.
     */
    public NotAllowedToCreateOfferException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Exception handler method for handling NotAllowedToCreateOfferException.
     * Converts the exception into an ApiErrorFactory and returns it in a ResponseEntity.
     *
     * @param ex The NotAllowedToCreateOfferException to handle.
     * @return ResponseEntity with ApiErrorFactory representing the error.
     */
    @ExceptionHandler(NotAllowedToCreateOfferException.class)
    public ResponseEntity<ApiErrorFactory> handleNotAllowedToCreateOfferException(NotAllowedToCreateOfferException ex) {
        ApiErrorFactory apiError = new ApiErrorFactory(
                HttpStatus.PAYMENT_REQUIRED,
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.PAYMENT_REQUIRED);
    }
}