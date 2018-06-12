package com.example.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * For handling validation errors
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        CustomFieldErrorResponse errorResponse = new CustomFieldErrorResponse(new Date(), "Validation Error");
        ex.getBindingResult().getFieldErrors().stream().forEach(fieldError ->
                errorResponse.addFieldError(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
