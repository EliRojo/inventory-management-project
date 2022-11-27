package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supplier")

public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    //le digo que esta mapeado por el campo supplier y con fetch eleijo si cargar las facturas o no
    private List<SummaryReport> summaryReports;


    public Supplier() {

    }

    public Supplier(Integer id, String company, String location, String contact, String status, List<SummaryReport> summaryReports) {
        this.id = id;
        this.company = company;
        this.address = location;
        this.contact = contact;
        this.status = status;
        this.summaryReports = summaryReports;
    }


    public Integer getId() {
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

    public List<SummaryReport> getSummaryReports() {
        if (summaryReports == null)
            summaryReports = new ArrayList<>();

        return summaryReports;
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
