package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.util.List;

public class SummaryReportDTO {

    @JsonAlias("summary_id")
    private String summaryId;

    @JsonAlias("operation_Type")
    private String operationType;
    private String date;

    @JsonAlias("invoice_amount")
    private double invoiceAmount;

    @JsonAlias("report_detail")
    private List<ReportDetailDTO> reportDetailDTOS;



    public SummaryReportDTO(){

    }

    public SummaryReportDTO(String summaryId, String operationType, String date, double invoiceAmount, List<ReportDetailDTO> reportDetailDTOS) {
        this.summaryId = summaryId;
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
        this.reportDetailDTOS = reportDetailDTOS;
    }

    public SummaryReportDTO(String summaryId, String operationType, String date, double invoiceAmount) {
        this.summaryId = summaryId;
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
    }

    public SummaryReportDTO(String operationType, String date, List<ReportDetailDTO> reportDetailDTOS) {
        this.operationType = operationType;
        this.date = date;
        this.reportDetailDTOS = reportDetailDTOS;
    }

    public void setSummaryId(String id) {
        this.summaryId = summaryId;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public void setReportDetailDTOS(List<ReportDetailDTO> reportDetailDTOS) {
        this.reportDetailDTOS = reportDetailDTOS;
    }

    public String getSummaryId() {
        return summaryId;
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

    public List<ReportDetailDTO> getReportDetailDTOS() {
        return reportDetailDTOS;
    }
}


