package com.devsuperior.dslearn.resources.exceptions;

import java.time.Instant;
import javax.servlet.http.HttpServletRequest;

import com.devsuperior.dslearn.services.exceptions.DatabaseException;
import com.devsuperior.dslearn.services.exceptions.EntityNotFoundException;
import com.devsuperior.dslearn.services.exceptions.ForbiddenException;
import com.devsuperior.dslearn.services.exceptions.UnauthorizedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundEXception(EntityNotFoundException e,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setTimeStamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Resource not Found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimeStamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Database exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationException(MethodArgumentNotValidException e,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError();
        validationError.setTimeStamp(Instant.now());
        validationError.setStatus(status.value());
        validationError.setError("Validation exception");
        validationError.setMessage(e.getMessage());
        validationError.setPath(request.getRequestURI());

        //@formatter:off
        e.getBindingResult().getFieldErrors()
                            .forEach( error -> validationError
                                              .addError(error.getField(), error.getDefaultMessage())
                                    );
        //@formatter:on

        return ResponseEntity.status(status).body(validationError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> IlegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimeStamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Bad Request");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e, HttpServletRequest request) {
        OAuthCustomError error = new OAuthCustomError("Forbideen", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<OAuthCustomError> unauthorized(UnauthorizedException e, HttpServletRequest request) {
        OAuthCustomError error = new OAuthCustomError("Unauthorized", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
