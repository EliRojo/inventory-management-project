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


    @OneToMany(mappedBy = "summaryReport" , fetch = FetchType.LAZY, // map se indica el campo en la entidad  q se esta mape
            cascade = CascadeType.REMOVE)
    private List<ReportDetail> reportDetails;



    public SummaryReport(){

    }


    public SummaryReport(String operationType, LocalDate date, Double invoiceAmount) {
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;


    }

    public SummaryReport(String operationType, LocalDate date, Double invoiceAmount, List<ReportDetail> reportDetails) {
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
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


    public List<ReportDetail> getReportDetails() {
        if (reportDetails == null)
            reportDetails = new ArrayList<>();

        return reportDetails;
    }
}
