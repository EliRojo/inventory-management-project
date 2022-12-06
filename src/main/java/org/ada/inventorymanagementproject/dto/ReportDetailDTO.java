package org.ada.inventorymanagementproject.dto;

import javax.persistence.*;


public class ReportDetailDTO {

    private Integer id;
    private Integer quantity;
    private String item;
    private String operationType;


    public ReportDetailDTO() {

    }

    public ReportDetailDTO(int quantity) {

        this.quantity = quantity;
    }

    public ReportDetailDTO(Integer quantity, String item, String operationType) {
        this.quantity = quantity;
        this.item = item;
        this.operationType = operationType;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getItem() {
        return item;
    }
}