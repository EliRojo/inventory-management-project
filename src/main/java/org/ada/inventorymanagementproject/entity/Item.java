package org.ada.inventorymanagementproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private double price;

    @Column(nullable = false)
    private String status;

    private String description;

    public Item() {
    }

    public Item(String code, String name, String stock, double price, String status, String description) {
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
