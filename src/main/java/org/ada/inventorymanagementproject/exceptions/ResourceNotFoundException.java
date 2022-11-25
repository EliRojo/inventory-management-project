package org.ada.inventorymanagementproject.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public static final String MESSAGE = "El recurso que está buscando no existe.";

    public ResourceNotFoundException(){
        super(MESSAGE);
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
