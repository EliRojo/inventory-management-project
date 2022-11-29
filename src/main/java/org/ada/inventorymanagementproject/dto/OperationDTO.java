package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class OperationDTO {

    private Integer id;
    @JsonAlias("summary_reports")
    private List<SummaryReportDTO> summaryReportDTOS;
    @JsonAlias("report-details")
    private List<ReportDetailDTO> reportDetailDTOS;

    public OperationDTO(List<SummaryReportDTO> summaryReportDTOS, List<ReportDetailDTO> reportDetailDTOS) {
        this.summaryReportDTOS = summaryReportDTOS;
        this.reportDetailDTOS = reportDetailDTOS;
    }

    public List<SummaryReportDTO> getSummaryReportDTOS() {
        return summaryReportDTOS;
    }

    public List<ReportDetailDTO> getReportDetailDTOS() {
        return reportDetailDTOS;
    }

    public Integer getId() {
        return id;
    }
}
