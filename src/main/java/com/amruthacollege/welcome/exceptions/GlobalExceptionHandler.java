package com.amruthacollege.welcome.exceptions;

import com.amruthacollege.welcome.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception Handler which handles all runtime exceptions like
 * {@link UserAuthenticationException}, {@link MailSendingException}, {@link UserNotFoundException},
 * {@link InvalidCredentialsException}, {@link WelcomeApplicationException}
 * layer itself.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<Response> handelAllAuthenticationExceptions( UserAuthenticationException e ) {
        return ResponseEntity.status (HttpStatus.UNAUTHORIZED).body (new Response (e.getMessage (), e.getHttpStatus ()));
    }

    @ExceptionHandler(MailSendingException.class)
    public ResponseEntity<Response> handelAllMailSendingExceptions( MailSendingException e ) {
        return ResponseEntity.status (HttpStatus.BAD_GATEWAY).body (new Response (e.getMessage (), e.getHttpStatus ()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Response> handelAllUserCredentialMismatchExceptions( InvalidCredentialsException e ) {
        return ResponseEntity.status (HttpStatus.UNPROCESSABLE_ENTITY).body (new Response (e.getMessage (), e.getHttpStatus ()));
    }

}