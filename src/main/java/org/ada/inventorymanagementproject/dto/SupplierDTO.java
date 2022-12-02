package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

public class SupplierDTO {


    private String id;
    private String company;
    private String address;
    private String contact;
    private String status;

    public SupplierDTO(){

    }
    public SupplierDTO(String id, String company, String address, String contact, String status) {

        this.id = id;
        this.company = company;
        this.address = address;
        this.contact = contact;
        this.status = status;

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


    public void setId(String id) {
        this.id = id;
    }


}
