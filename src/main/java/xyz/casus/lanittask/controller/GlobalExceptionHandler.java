package xyz.casus.lanittask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Void> handleInvalidFormatException(JsonProcessingException exc) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
