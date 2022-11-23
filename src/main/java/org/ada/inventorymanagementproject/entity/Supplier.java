package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supplier")

public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String supplierCode;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String direction;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "supplier" , fetch = FetchType.LAZY,
                cascade = CascadeType.REMOVE) //le digo que esta mapeado por el campo supplier y con fetch eleijo si cargar las facturas o no
    private List<SummaryReport> summaryReports;


    public Supplier (){

    }

    public Supplier(String supplierCode, String company,
                    String direction, String contact,
                    String status) {
        this.supplierCode = supplierCode;
        this.company = company;
        this.direction = direction;
        this.contact = contact;
        this.status = status;
    }

    public Supplier(String supplierCode, String company, String direction, String contact, String status,
                    List<SummaryReport> summaryReports) {
        this.supplierCode = supplierCode;
        this.company = company;
        this.direction = direction;
        this.contact = contact;
        this.status = status;
        this.summaryReports = summaryReports;
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

    public List<SummaryReport> getSummaryReports() {
        if (summaryReports == null)
            summaryReports = new ArrayList<>();

        return summaryReports;
    }

}
