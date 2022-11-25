package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Summary_Report ")
public class SummaryReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//lo tengo que mapear con grapers

    @Column(name = "operation_type" , nullable = false)
    private String operationType;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "invoice_amount" ,nullable = false)
    private Double invoiceAmount;


    //le digo q es una relacion
    @ManyToOne(fetch = FetchType.EAGER) //aca le digo que siempre que tenga una factura voy a cargar el proveedor asociado
    @JoinColumn(name = "supplier_id" , nullable = false) //le defino que le pertenece a esa columna y que si o si tengo que asociar
    private Supplier supplier;

    @OneToMany(mappedBy = "report_detail" , fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<ReportDetail> reportDetails;



    public SummaryReport(){

    }


    public SummaryReport(String operationType, LocalDate date, Double invoiceAmount, Supplier supplier) {
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
        this.supplier = supplier;

    }

    public SummaryReport(String operationType, LocalDate date, Double invoiceAmount, List<ReportDetail> reportDetails) {
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
        this.reportDetails = reportDetails;
    }

    public SummaryReport(String operationType, LocalDate date, Double invoiceAmount, Supplier supplier, List<ReportDetail> reportDetails) {
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
        this.supplier = supplier;
        this.reportDetails = reportDetails;
    }

    public Integer getId() {
        return id;
    }

    public String getOperationType() {
        return operationType;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public Supplier getSupplier() {
        return supplier;
    }


    public List<ReportDetail> getReportDetails() {
        if (reportDetails == null)
            reportDetails = new ArrayList<>();

        return reportDetails;
    }
}
