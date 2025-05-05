package com.nefisa.feelingstracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeelingNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFeelingNotFoundException(FeelingNotFoundException exc){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
        System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJwtException(InvalidJwtException exc){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exc){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Request",
                System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
