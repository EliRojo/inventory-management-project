package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

public class SupplierDTO {


    private Integer id;
    private String company;
    private String address;
    private String contact;
    private String status;

    @JsonAlias("summary_reports")
    private List<SummaryReportDTO> summaryReportDTOS;

    public SupplierDTO(){

    }
    public SupplierDTO(Integer id, String company, String direction, String contact, String status,
                       List<SummaryReportDTO> summaryReportDTOS) {
        this.id = id;
        this.company = company;
        this.address = direction;
        this.contact = contact;
        this.status = status;
        this.summaryReportDTOS = summaryReportDTOS;
    }


    public Integer getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getDirection() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getStatus() {
        return status;
    }

    public List<SummaryReportDTO> getSummaryReportDTOS() {
        return summaryReportDTOS;
    }
}
