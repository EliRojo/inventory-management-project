package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.ReportDetailDTO;

import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.entity.ReportDetail;
import org.ada.inventorymanagementproject.entity.SummaryReport;
import org.ada.inventorymanagementproject.entity.Supplier;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.ReportDetailRepository;
import org.ada.inventorymanagementproject.repository.SummaryReportRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportDetailService {
    private final ReportDetailRepository reportDetailRepository;
    private final SummaryReportRepository summaryReportRepository;

    public ReportDetailService(ReportDetailRepository reportDetailRepository, SummaryReportRepository summaryReportRepository) {
        this.reportDetailRepository = reportDetailRepository;
        this.summaryReportRepository = summaryReportRepository;
    }

    public void  create(ReportDetailDTO reportDetailDTO , Integer summaryReportId){
        Optional<SummaryReport> summaryReport = summaryReportRepository.findById(summaryReportId);
        if (summaryReport.isEmpty()){
            throw new ResourceNotFoundException();
        }

       ReportDetail reportDetail = mapToEntity(reportDetailDTO , summaryReport.get());
        reportDetail = reportDetailRepository.save(reportDetail);
        reportDetailDTO.setId(reportDetail.getId());


    }

    public void create (List<ReportDetailDTO> reportDetailDTOS, SummaryReport summaryReport ){ //recibe ese proveedor creado en la bd, crea una lista de summary
        List<ReportDetail> reportDetail = reportDetailDTOS.stream()
                .map(reportDetailDTO -> mapToEntity(reportDetailDTO, summaryReport))
                .collect(Collectors.toList());
        reportDetailRepository.saveAll(reportDetail);
    }

    public ReportDetailDTO retrieveById(Integer summaryReportId, Integer reportDetailId) { //busca, obtiene summaryreports relacionado a un supplier
        if (!reportDetailRepository.existsById(summaryReportId)){
            throw new ResourceNotFoundException("El summary report no existe.");
        }
        Optional<ReportDetail> reportDetail = reportDetailRepository.findById(reportDetailId);
        if (reportDetail.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return mapToDTO(reportDetail.get());
    }

    public void delete(Integer reportDetailId) {
        try {
            reportDetailRepository.deleteById(reportDetailId);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException();
        }
    }


    private ReportDetailDTO mapToDTO(ReportDetail reportDetail) {

        ReportDetailDTO reportDetailDTO = new ReportDetailDTO(reportDetail.getId(),reportDetail.getQuantity());

        return reportDetailDTO;
    }
    private ReportDetail mapToEntity(ReportDetailDTO reportDetailDTO , SummaryReport summaryReport) {
        ReportDetail reportDetail = new ReportDetail(reportDetailDTO.getId(),reportDetailDTO.getQuantity());

        return reportDetail;
    }





   /* public ReportDetailDTO create(ReportDetailDTO reportDetailDTO){
        ReportDetail reportDetail = mapToEntity(reportDetailDTO);
        checkForExistingRepotDeil(reportDetail.getId());
        reportDetail = reportDetailRepository.save(reportDetail);

        return reportDetailDTO;
    }
    public List<ReportDetailDTO> retrieveAll(){ //
        List<ReportDetail> reportDetails = reportDetailRepository.findAll();
        return reportDetails.stream()
                .map(reportDetail -> mapToDTO(reportDetail))
                .collect(Collectors.toList());
    }
    public ReportDetailDTO retrieveByCode(Integer id){ //
        Optional<ReportDetail> reportDetail = reportDetailRepository.findById(id);

        if(reportDetail.isEmpty()){
            throw new ResourceNotFoundException();
        }

        return mapToDTO(reportDetail.get());
    }
    public List<ReportDetailDTO> retrieveById(Integer id){ //SI NO HAY NADA CREADO DEBE LANZAR UNA EXCEPCION

        Optional<ReportDetail> reportDetails = reportDetailRepository.findById(id);

        if(reportDetails.isEmpty()){
            throw new ResourceNotFoundException();
        }

        return reportDetails.stream()
                .map(reportDetail -> mapToDTO(reportDetail))
                .collect(Collectors.toList());
    }
    private void checkForExistingRepotDeil(Integer id) {
        if(reportDetailRepository.existsById(id)){
            throw new ExistingResourceException();
        }
    }*/




}
