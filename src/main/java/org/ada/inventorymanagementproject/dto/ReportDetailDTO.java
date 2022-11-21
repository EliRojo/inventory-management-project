package org.ada.inventorymanagementproject.dto;

import javax.persistence.*;


public class ReportDetailDTO {
    private Integer id;
    private int quantity;

    public ReportDetailDTO(Integer id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
