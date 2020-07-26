package com.amruthacollege.welcome.exceptions;

import org.springframework.http.HttpStatus;

/**
 * This class extends {@link WelcomeApplicationException} and creates a custom exception
 * which would be thrown during the credentials mismatch
 */
public class InvalidCredentialsException extends WelcomeApplicationException {

    /**
     * Constructor takes message and Status code as input parameter and fetch
     * message from its superclass.
     *
     * @param message    as String input parameter
     * @param httpStatus as Integer input parameter
     */
    public InvalidCredentialsException( String message, HttpStatus httpStatus ) {
        super (message, httpStatus);
    }
}