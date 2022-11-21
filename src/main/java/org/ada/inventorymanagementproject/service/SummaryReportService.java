package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.entity.SummaryReport;
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
    private final SummaryReportRepository SummaryReportRepository;

    public SummaryReportService(SummaryReportRepository summaryReportRepository) {
        SummaryReportRepository = summaryReportRepository;
    }

    public SummaryReportDTO create(SummaryReportDTO summaryReportDTO) {
        SummaryReport summaryReport = mapToEntity(summaryReportDTO);

        summaryReport = SummaryReportRepository.save(summaryReport);

        return summaryReportDTO;
    }


    public List<SummaryReportDTO> retrieveAll() { //el dto porque me comunico asi con el cliente, sin parametros porque traigo a todos sin filtros
        List<SummaryReport> summaryReports = SummaryReportRepository.findAll();
        return summaryReports.stream() //la lista lo convierto en un stream luego lo mapeo
                .map(summaryReport -> mapToDTO(summaryReport)) //mapeo cada summary y luego
                .collect(Collectors.toList()); //hago un colec y lo meto en una lista
    }

    public SummaryReportDTO retrieveById (Integer id) {
        Optional<SummaryReport> summaryReport = SummaryReportRepository.findById(id); //opcional, porque puedo o no recibir una respuesta

        if (summaryReport.isEmpty()) {  //si esta vacio
            throw new ResourceNotFoundException();
        }

        return mapToDTO(summaryReport.get());
    }


    private SummaryReport mapToEntity(SummaryReportDTO summaryReportDTO) {

        SummaryReport summaryReport = new SummaryReport(summaryReportDTO.getId() ,
                summaryReportDTO.getOperationType(), LocalDate.parse(summaryReportDTO.getDate(), DATE_TIME_FORMATTER),
                summaryReportDTO.getInvoiceAmount());

        return  summaryReport;
    }


    private SummaryReportDTO mapToDTO(SummaryReport summaryReport) {

        SummaryReportDTO summaryReportDTO = new SummaryReportDTO(summaryReport.getId() , summaryReport.getOperationType() ,
                summaryReport.getDate().toString() , summaryReport.getInvoiceAmount());

        return  summaryReportDTO;
    }


}
