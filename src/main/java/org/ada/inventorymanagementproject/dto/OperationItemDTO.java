package org.ada.inventorymanagementproject.dto;

public class OperationItemDTO {

    private String code;
    private Integer quantity;


    public OperationItemDTO(){

    }
    public OperationItemDTO(String code, Integer quantity) {
        this.code = code;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(String code) {
        this.code = code;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}