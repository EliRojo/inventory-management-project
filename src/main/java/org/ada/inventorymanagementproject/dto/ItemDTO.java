package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class ItemDTO {

        private String code;
        private String name;
        private String stock;
        private double price;
        private String status;
        private String description;
      //  @JsonAlias("report_detail")
      //  private List<ReportDetailDTO> reportDetailDTOS;

    public ItemDTO(){}

    public ItemDTO(String code, String name, String stock, double price, String status, String description) {
        this.code = code;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
