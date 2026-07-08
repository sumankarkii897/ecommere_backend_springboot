package com.sumankarki.Ecommerce.exception;

import com.sumankarki.Ecommerce.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllException(Exception ex, WebRequest request){
        Response errorResponse = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Response errorResponse = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<Response> handleInvalidCredentialException(InvalidCredentialException ex, WebRequest request){
        Response errorResponse = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        Response response = Response.builder()
                .status(400)
                .message(message)
                .build();

        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Response> handleAlreadyExistsException(AlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Response.builder()
                        .status(409)
                        .message(ex.getMessage())
                        .build());
    }
}
