package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;

public class SummaryReportDTO {

    private Integer  id;

    @JsonAlias("operation_Type")
    private String operationType;
    private String date;

    @JsonAlias("invoice_amount")
    private double invoiceAmount;

    public SummaryReportDTO(){}

    public SummaryReportDTO(Integer id, String operationType, String date, double invoiceAmount) {
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

    public String getDate() {
        return date;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }
}
