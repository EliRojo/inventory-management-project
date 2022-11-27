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
    public SupplierDTO(String company, String address, String contact, String status,
                       List<SummaryReportDTO> summaryReportDTOS) {

        this.company = company;
        this.address = address;
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

    public String getAddres() {
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

    public void setId(Integer id) {
        this.id = id;
    }


}
