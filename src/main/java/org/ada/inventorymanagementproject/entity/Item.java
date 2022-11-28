package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;
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
    private String stock;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String status;

    private String description;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ReportDetail> reportDetails;



    public Item() {
    }

    public Item(String code, String name, String stock, Double price, String status, String description) {
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


}
