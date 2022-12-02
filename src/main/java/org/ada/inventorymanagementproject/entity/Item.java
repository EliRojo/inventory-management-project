package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {

    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String status;

    private String description;

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<ReportDetail> reportDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    public Item() {
    }

    public Item(String code, String name, Integer stock, Double price, String status, String description) {
        this.code = code;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.description = description;
    }

    public Item(String code, String name, Integer stock, Double price, String status, String description, List<ReportDetail> reportDetails) {
        this.code = code;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.description = description;
        this.reportDetails = reportDetails;
    }

    public Item(String code, String name, Integer stock, Double price, String status, String description, List<ReportDetail> reportDetails, Supplier supplier) {
        this.code = code;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.description = description;
        this.reportDetails = reportDetails;
        this.supplier = supplier;
    }

    public Item(String code, String name, Integer stock, Double price, String status, String description, Supplier supplier) {
        this.code = code;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.description = description;
        this.supplier = supplier;
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

    public List<ReportDetail> getReportDetails(){
        if(reportDetails == null){
            reportDetails = new ArrayList<>();
        }

        return reportDetails;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReportDetails(List<ReportDetail> reportDetails) {
        this.reportDetails = reportDetails;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }



    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "name":
                this.name = (String) newValue;
                break;
            case "stock":
                this.stock = (Integer) newValue;
                break;
            case "price":
                this.price = (Double) newValue;
                break;
            case "status":
                this.status = (String) newValue;
                break;
            case "description":
                this.description = (String) newValue;
                break;
        }
    }
}
