package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.ReportDetailDTO;


import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.entity.Item;
import org.ada.inventorymanagementproject.entity.ReportDetail;
import org.ada.inventorymanagementproject.entity.SummaryReport;
import org.ada.inventorymanagementproject.entity.Supplier;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.ItemRepository;
import org.ada.inventorymanagementproject.repository.ReportDetailRepository;
import org.ada.inventorymanagementproject.repository.SummaryReportRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportDetailService {

    private final ReportDetailRepository reportDetailRepository;
    private final SummaryReportRepository summaryReportRepository;
    private final ItemRepository itemRepository;

    public ReportDetailService(ReportDetailRepository reportDetailRepository, ItemRepository itemRepository, SummaryReportRepository summaryReportRepository) {
        this.reportDetailRepository = reportDetailRepository;
        this.itemRepository = itemRepository;
        this.summaryReportRepository = summaryReportRepository;
    }


    public ReportDetailDTO retrieveById(String itemCode, Integer reportDetailId) {
        if (!itemRepository.existsById(itemCode)) {
            throw new ResourceNotFoundException("El código del item que está buscando no existe.");
        }

        Optional<ReportDetail> reportDetail = reportDetailRepository.findById(reportDetailId);

        if (reportDetail.isEmpty()) {
            throw new ResourceNotFoundException("El id del reporte detalle que está buscando no existe.");
        }

        return mapToDTO(reportDetail.get());
    }


    public List<ReportDetailDTO> mapToDTOS(List<ReportDetail> reportDetails) {

        return reportDetails.stream()
                .map(reportDetail -> mapToDTO(reportDetail))
                .collect(Collectors.toList());

    }

    public void delete(Integer reportDetailId) {
        try {
            reportDetailRepository.deleteById(reportDetailId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
        }
    }

    public void replace(Integer reportDetailId, ReportDetailDTO reportDetailDTO) {
        Optional<ReportDetail> reportDetail = reportDetailRepository.findById(reportDetailId);
        if (reportDetail.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        ReportDetail reportDetailToReplace = reportDetail.get();
        reportDetailToReplace.setQuantity(reportDetailDTO.getQuantity());

        reportDetailRepository.save(reportDetailToReplace);
    }

    public void modify(Integer reportDetailId, Map<String, Object> fieldsToModify) {
        Optional<ReportDetail> reportDetail = reportDetailRepository.findById(reportDetailId);
        if (reportDetail.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        ReportDetail reportDetailToModify = reportDetail.get();
        fieldsToModify.forEach((key, value) -> reportDetailToModify.modifyAttributeValue(key, value));
        reportDetailRepository.save(reportDetailToModify);
    }


    private ReportDetailDTO mapToDTO(ReportDetail reportDetail) {

        ReportDetailDTO reportDetailDTO = new ReportDetailDTO(reportDetail.getQuantity(),
                reportDetail.getItem().getName(),
                reportDetail.getSummaryReport().getOperationType());


        reportDetailDTO.setId(reportDetail.getId());
        return reportDetailDTO;
    }

    private ReportDetail mapToEntity(ReportDetailDTO reportDetailDTO, Item item) {

        ReportDetail reportDetail = new ReportDetail(reportDetailDTO.getQuantity(), item);
        return reportDetail;
    }

    private ReportDetail mapToEntity(ReportDetailDTO reportDetailDTO, SummaryReport summaryReport) {
        ReportDetail reportDetail = new ReportDetail(reportDetailDTO.getQuantity(), summaryReport);

        return reportDetail;
    }


}
