package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.ReportDetailDTO;

import org.ada.inventorymanagementproject.entity.ReportDetail;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.ReportDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ReportDetailService {
    private final ReportDetailRepository reportDetailRepository;

    public ReportDetailService(ReportDetailRepository reportDetailRepository) {
        this.reportDetailRepository = reportDetailRepository;
    }
    public ReportDetailDTO create(ReportDetailDTO reportDetailDTO){
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
    }
    private ReportDetailDTO mapToDTO(ReportDetail reportDetail) {

        ReportDetailDTO reportDetailDTO = new ReportDetailDTO(reportDetail.getId(),reportDetail.getQuantity());

        return reportDetailDTO;
    }
    private ReportDetail mapToEntity(ReportDetailDTO reportDetailDTO) {
        ReportDetail reportDetail = new ReportDetail(reportDetailDTO.getId(),reportDetailDTO.getQuantity());

        return reportDetail;
    }


}
