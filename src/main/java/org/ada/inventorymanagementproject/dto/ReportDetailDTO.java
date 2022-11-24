package org.ada.inventorymanagementproject.dto;

import javax.persistence.*;


public class ReportDetailDTO {

    private Integer id;
    private int quantity;

    public ReportDetailDTO(){

    }

    public ReportDetailDTO(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {

        return quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
