package org.ada.inventorymanagementproject.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class SupplierDTO {

    private String supplierCode;
    private String company;
    private String direction;
    private String contact;
    private String status;

    public SupplierDTO(String supplierCode, String company, String direction, String contact, String status) {
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
