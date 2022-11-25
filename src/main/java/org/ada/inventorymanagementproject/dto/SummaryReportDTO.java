package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.util.List;

public class SummaryReportDTO {

    private Integer  id;

    @JsonAlias("operation_Type")
    private String operationType;
    private String date;

    @JsonAlias("invoice_amount")
    private double invoiceAmount;

    @JsonAlias("report_detail")
    private List<ReportDetailDTO> reportDetailDTOS;

    public SummaryReportDTO(){}

    public SummaryReportDTO(String operationType, String date, double invoiceAmount) {
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
    }

    public void setId(Integer id) {
        this.id = id;
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
