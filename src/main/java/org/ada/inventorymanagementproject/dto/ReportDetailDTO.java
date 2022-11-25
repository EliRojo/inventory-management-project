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

<<<<<<< HEAD
    public void setId(Integer id) {
        this.id = id;
=======
    public int getQuantity() {

        return quantity;
>>>>>>> cfce3e5f5cb6a2ea8a7d8f63f9da6f4092b168ab
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
