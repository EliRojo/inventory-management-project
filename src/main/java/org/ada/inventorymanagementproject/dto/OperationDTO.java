package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class OperationDTO {

    private String id;

    @JsonAlias("summary_id")
    private String summaryId;

    @JsonAlias("operation_Type")
    private String operationType;
    private String date;

    @JsonAlias("invoice_amount")
    private double invoiceAmount;

    @JsonAlias("report_detail")
    private List<OperationItemDTO> itemDTOS;


    public String getOperationType() {
        return operationType;
    }

    public String getDate() {
        return date;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public List<OperationItemDTO> getItemDTOS() {
        return itemDTOS;
    }

    public String getId() {
        return id;
    }

    public String getSummaryId() {
        return summaryId;
    }
}
