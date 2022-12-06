package org.ada.inventorymanagementproject.exceptions;

public class ExistingResourceException extends RuntimeException {


    public static final String MESSAGE = "El recurso se está intentando crear ya existe.";


    public  ExistingResourceException() {
    }

    public ExistingResourceException(String message) {
        super(message);
    }
}
