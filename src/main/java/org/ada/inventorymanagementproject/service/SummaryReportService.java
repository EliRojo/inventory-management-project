package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.entity.SummaryReport;
import org.ada.inventorymanagementproject.entity.Supplier;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.SummaryReportRepository;
import org.ada.inventorymanagementproject.repository.SupplierRepository;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private final SupplierRepository supplierRepository;

    public SummaryReportService(SummaryReportRepository summaryReportRepository, SupplierRepository supplierRepository) {
        this.summaryReportRepository = summaryReportRepository;
        this.supplierRepository = supplierRepository;
    }

    public void  create(SummaryReportDTO summaryReportDTO , Integer supplierId){  //crea un summary relacionado a un supplier
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()){
            throw new ResourceNotFoundException();
        }

        SummaryReport summaryReport = mapToEntity(summaryReportDTO , supplier.get());
        summaryReport = summaryReportRepository.save(summaryReport);
        summaryReportDTO.setId(summaryReport.getId());


    }


    public void create (List<SummaryReportDTO> summaryReportDTOS, Supplier supplier ){ //recibe ese proveedor creado en la bd, crea una lista de summary
        List<SummaryReport> summaryReports = summaryReportDTOS.stream()
                .map(summaryReportDTO -> mapToEntity(summaryReportDTO, supplier))
                .collect(Collectors.toList());
        summaryReportRepository.saveAll(summaryReports);
    }

    public SummaryReportDTO retrieveById(Integer supplierId, Integer summaryReportId) { //busca, obtiene summaryreports relacionado a un supplier
        if (!summaryReportRepository.existsById(supplierId)){
            throw new ResourceNotFoundException("El proveedor no existe.");
        }
        Optional<SummaryReport> summaryReport = summaryReportRepository.findById(summaryReportId);
        if (summaryReport.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return mapToDTO(summaryReport.get());
    }

    public void delete(Integer summaryReportId) {  //elimina summaryReports por id
        try {
            summaryReportRepository.deleteById(summaryReportId);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException();
        }
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
