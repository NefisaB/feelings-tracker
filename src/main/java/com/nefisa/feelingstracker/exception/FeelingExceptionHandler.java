package com.nefisa.feelingstracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FeelingExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<FeelingErrorResponse> handleFeelingNotFoundException(FeelingNotFoundException exc){
        FeelingErrorResponse feelingErrorResponse = new FeelingErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
        System.currentTimeMillis());

        return new ResponseEntity<>(feelingErrorResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<FeelingErrorResponse> handleGenericException(Exception exc){
        FeelingErrorResponse feelingErrorResponse = new FeelingErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Request",
                System.currentTimeMillis());

        return new ResponseEntity<>(feelingErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
