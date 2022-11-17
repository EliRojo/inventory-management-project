package org.ada.inventorymanagementproject.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public static final String MESSAGE = "El producto que está buscando no existe.";

    public ResourceNotFoundException(){

    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
