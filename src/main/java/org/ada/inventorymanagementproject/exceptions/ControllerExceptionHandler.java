package org.ada.inventorymanagementproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ExistingResourceException.class) //anotacion para indicar como se va a controlar la excepcion
    public ResponseEntity handleException (ExistingResourceException e) { //retorna un ResponseEntity porque va a retornar una respuesta de tipo HTTP

        return new ResponseEntity(ExistingResourceException.MESSAGE, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class) //anotacion para indicar como se va a controlar la excepcion
    public ResponseEntity handleException (ResourceNotFoundException e) { //retorna un ResponseEntity porque va a retornar una respuesta de tipo HTTP

        return new ResponseEntity(ResourceNotFoundException.MESSAGE, HttpStatus.NOT_FOUND);
    }
}

