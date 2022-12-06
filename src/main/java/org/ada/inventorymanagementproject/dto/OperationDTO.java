
package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class OperationDTO {


    @JsonAlias("summary_id")
    private String summaryId;

    @JsonAlias("type")
    private String operationType;

    private String date;

    @JsonAlias("invoice_amount")
    private double invoiceAmount;

    @JsonAlias("items")
    private List<OperationItemDTO> itemDTOS;

    public OperationDTO(){

    }

    public OperationDTO(String summaryId, String operationType,
                        String date, double invoiceAmount, List<OperationItemDTO> itemDTOS) {
        this.summaryId = summaryId;
        this.operationType = operationType;
        this.date = date;
        this.invoiceAmount = invoiceAmount;
        this.itemDTOS = itemDTOS;
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

    public List<OperationItemDTO> getItemDTOS() {
        return itemDTOS;
    }


    public String getSummaryId() {
        return summaryId;
    }

}