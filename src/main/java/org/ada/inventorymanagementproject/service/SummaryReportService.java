package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.entity.SummaryReport;
import org.ada.inventorymanagementproject.entity.Supplier;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.SummaryReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SummaryReportService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final SummaryReportRepository summaryReportRepository;

    public SummaryReportService(SummaryReportRepository summaryReportRepository) {
        this.summaryReportRepository = summaryReportRepository;
    }

    public void create (List<SummaryReportDTO> summaryReportDTOS, Supplier supplier ){ //recibe ese proveedor creado en la bd
        List<SummaryReport> summaryReports = summaryReportDTOS.stream()
                .map(summaryReportDTO -> mapToEntity(summaryReportDTO, supplier))
                .collect(Collectors.toList());
        summaryReportRepository.saveAll(summaryReports);
    }



    public List<SummaryReportDTO> mapToDTOS(List<SummaryReport> summaryReports) {

        return  summaryReports.stream()
                .map(summaryReport -> mapToDTO(summaryReport))
                .collect(Collectors.toList());
    }

    private SummaryReport mapToEntity(SummaryReportDTO summaryReportDTO, Supplier supplier) {

        SummaryReport summaryReport = new SummaryReport(summaryReportDTO.getId() ,
                summaryReportDTO.getOperationType(), LocalDate.parse(summaryReportDTO.getDate(), DATE_TIME_FORMATTER),
                summaryReportDTO.getInvoiceAmount(), supplier);

        return  summaryReport;
    }

    private SummaryReportDTO mapToDTO(SummaryReport summaryReport){
        SummaryReportDTO summaryReportDTO = new SummaryReportDTO(summaryReport.getId() , summaryReport.getOperationType(),
                summaryReport.getDate().toString(), summaryReport.getInvoiceAmount());

      return summaryReportDTO;
    }



}
