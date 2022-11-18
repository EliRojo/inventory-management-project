package org.ada.inventorymanagementproject.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class Supplier {
    @Id
    private String supplierCode;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String direction;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String status;

    public Supplier(String supplierCode, String company, String direction, String contact, String status) {
        this.supplierCode = supplierCode;
        this.company = company;
        this.direction = direction;
        this.contact = contact;
        this.status = status;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public String getCompany() {
        return company;
    }

    public String getDirection() {
        return direction;
    }

    public String getContact() {
        return contact;
    }

    public String getStatus() {
        return status;
    }
}
