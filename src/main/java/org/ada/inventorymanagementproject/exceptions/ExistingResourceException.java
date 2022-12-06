package org.ada.inventorymanagementproject.exceptions;

public class ExistingResourceException extends RuntimeException{

    public static final String MESSAGE = "El recurso que está intentando crear ya existe.";

    public ExistingResourceException() {
        super(MESSAGE);
    }
    public ExistingResourceException(String message) {
        super(message);
    }
}
