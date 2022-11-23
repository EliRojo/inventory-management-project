package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

public class SupplierDTO {

    private String supplierCode;
    private String company;
    private String direction;
    private String contact;
    private String status;

    @JsonAlias("summary_reports")
    private List<SummaryReportDTO> summaryReportDTO;


    public SupplierDTO(String supplierCode, String company, String direction, String contact, String status,
                       List<SummaryReportDTO> summaryReportDTO) {
        this.supplierCode = supplierCode;
        this.company = company;
        this.direction = direction;
        this.contact = contact;
        this.status = status;
        this.summaryReportDTO = summaryReportDTO;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public String getCompany() {
        return company;
    }

    public String getDirection() {
        return direction;
    }

    public String getContact() {
        return contact;
    }

    public String getStatus() {
        return status;
    }

    public List<SummaryReportDTO> getSummaryReportDTO() {
        return summaryReportDTO;
    }
}
