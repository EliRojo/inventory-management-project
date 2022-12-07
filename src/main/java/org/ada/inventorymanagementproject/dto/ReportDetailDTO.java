package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.*;


public class ReportDetailDTO {

    private Integer id;

    private Integer quantity;

    private String item;

    @JsonAlias("item_code")
    private String itemCode;




    public ReportDetailDTO() {

    }

    public ReportDetailDTO(int quantity) {

        this.quantity = quantity;
    }

    public ReportDetailDTO(Integer quantity, String item, String itemCode) {
        this.quantity = quantity;
        this.item = item;
        this.itemCode = itemCode;

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

    public String getItem() {
        return item;
    }

    public String getItemCode() {
        return itemCode;
    }


}