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
<<<<<<< HEAD


    public SummaryReportDTO(){

    }

    public SummaryReportDTO(Integer id, String operationType, String date,
                            double invoiceAmount, List<ReportDetailDTO> reportDetailDTOS) {
        this.id = id;
=======

    public SummaryReportDTO(){}

    public SummaryReportDTO(String operationType, String date, double invoiceAmount) {
>>>>>>> cfce3e5f5cb6a2ea8a7d8f63f9da6f4092b168ab
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
        this.reportDetailDTOS = reportDetailDTOS;
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

    public List<ReportDetailDTO> getReportDetailDTOS() {
        return reportDetailDTOS;
    }
}
