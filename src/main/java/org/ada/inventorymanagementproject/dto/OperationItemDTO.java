package org.ada.inventorymanagementproject.dto;

public class OperationItemDTO {

    private String id;
    private Integer cantidad;


    public String getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
