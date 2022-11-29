package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supplier")

public class Supplier {

    @Id
    private String id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<Item> items;


    public Supplier() {

    }

    public Supplier(String id, String company, String address, String contact, String status) {
        this.id = id;
        this.company = company;
        this.address = address;
        this.contact = contact;
        this.status = status;
    }

    public Supplier(String id, String company, String address, String contact, String status, List<Item> items) {
        this.id = id;
        this.company = company;
        this.address = address;
        this.contact = contact;
        this.status = status;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getStatus() {
        return status;
    }

    public List<Item> getItems() {
        if (items == null)
            items = new ArrayList<>();

        return items;
    }


    public void setCompany(String company) {
        this.company = company;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "company":
                this.company = (String) newValue;
                break;
            case "address":
                this.address = (String) newValue;
                break;
            case "contact":
                this.contact = (String) newValue;
                break;
            case "status":
                this.status = (String) newValue;
                break;

        }
    }
}
