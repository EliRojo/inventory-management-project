package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "summary_report ")
public class SummaryReport {

    @Id
    @Column(name = "summary_id", nullable = false)
    private String summaryId;

    @Column(name = "operation_type" , nullable = false)
    private String operationType;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "invoice_amount" ,nullable = false)
    private Double invoiceAmount;


    @OneToMany(mappedBy = "summaryReport" , fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<ReportDetail> reportDetails;



    public SummaryReport(){

    }


    public SummaryReport(String summaryId, String operationType, LocalDate date, Double invoiceAmount) {
        this.summaryId = summaryId;
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;


    }

    public SummaryReport(String summaryId, String operationType, LocalDate date, Double invoiceAmount, List<ReportDetail> reportDetails) {
        this.summaryId = summaryId;
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
        this.reportDetails = reportDetails;
    }


    public String getSummaryId() {
        return summaryId;
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

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public void setReportDetails(List<ReportDetail> reportDetails) {
        this.reportDetails = reportDetails;
    }

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "operation_type":
                this.operationType = (String) newValue;
                break;
            case "date":
                this.date = (LocalDate) newValue;
                break;
            case "invoice_amount":
                this.invoiceAmount = (Double) newValue;
                break;
        }
    }
}
