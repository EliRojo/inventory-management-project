package org.ada.inventorymanagementproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Table(name = "Summary_Report ")
public class SummaryReport {

    @Id
    private Integer id;

    @Column(name = "operation_type" , nullable = false)
    private String operationType;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "invoice_amount" ,nullable = false)
    private double invoiceAmount;

    public SummaryReport(){}

    public SummaryReport(Integer id, String operationType, LocalDate date, double invoiceAmount) {
        this.id = id;
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
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

    public double getInvoiceAmount() {
        return invoiceAmount;
    }
}
