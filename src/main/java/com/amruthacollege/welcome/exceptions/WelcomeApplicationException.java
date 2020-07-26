package com.amruthacollege.welcome.exceptions;

import org.springframework.http.HttpStatus;

/**
 * This class extends {@link RuntimeException} and creates a custom exception
 * which would be thrown during the credentials mismatch
 */
public class WelcomeApplicationException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;

    /**
     * Constructor takes message and Status code as input parameter and fetch
     * message from its superclass.
     *
     * @param message    as String input parameter
     * @param httpStatus as Integer input parameter
     */
    public WelcomeApplicationException( String message, HttpStatus httpStatus ) {
        super (message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() { return httpStatus; }
}
