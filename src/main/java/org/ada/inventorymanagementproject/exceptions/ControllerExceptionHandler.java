package org.ada.inventorymanagementproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //esta anotacion viene dentro de spring
public class ControllerExceptionHandler extends RuntimeException{

    @ExceptionHandler(ExistingResourceException.class)
    public ResponseEntity handleException (ExistingResourceException e) {

        return new ResponseEntity(ExistingResourceException.MESSAGE, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleException (ResourceNotFoundException e) {

        return new ResponseEntity(e.MESSAGE, HttpStatus.NOT_FOUND);
    }
}

