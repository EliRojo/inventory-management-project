package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.ada.inventorymanagementproject.entity.ReportDetail;

import java.util.Collection;
import java.util.List;

public class ItemDTO {

    private String code;
    private String name;
    private Integer stock;
    private Double price;
    private String status;
    private String description;
    @JsonAlias("report_details")
    private List<ReportDetailDTO> reportDetailDTOS;

    public ItemDTO(){

    }

    public ItemDTO(String code, String name, Integer stock, Double price, String status, String description, List<ReportDetailDTO> reportDetailDTOS) {
        this.code = code;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.description = description;
        this.reportDetailDTOS = reportDetailDTOS;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public List<ReportDetailDTO> getReportDetailDTOS() {
        return reportDetailDTOS;
    }

    public void setCode(String code) {
        this.code = code;
    }
}