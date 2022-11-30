package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.OperationDTO;
import org.ada.inventorymanagementproject.dto.OperationItemDTO;
import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.entity.Item;
import org.ada.inventorymanagementproject.entity.ReportDetail;
import org.ada.inventorymanagementproject.entity.SummaryReport;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.ItemRepository;
import org.ada.inventorymanagementproject.repository.ReportDetailRepository;
import org.ada.inventorymanagementproject.repository.SummaryReportRepository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OperationService {
    private final SummaryReportRepository summaryReportRepository;
    private final SummaryReport summaryReport;
    private final SummaryReportService summaryReportService;
    private final ItemRepository itemRepository;
    private final ReportDetailRepository reportDetailRepository;

    public OperationService(SummaryReportRepository summaryReportRepository, SummaryReport summaryReport, SummaryReportService summaryReportService,
                            ItemRepository itemRepository, ReportDetailRepository reportDetailRepository) {
        this.summaryReportRepository = summaryReportRepository;
        this.summaryReport = summaryReport;
        this.summaryReportService = summaryReportService;
        this.itemRepository = itemRepository;
        this.reportDetailRepository = reportDetailRepository;
    }


    public void create(OperationDTO operationDTO) {
        SummaryReport summaryReport = mapToEntity(operationDTO);
        summaryReport = summaryReportRepository.save(summaryReport)   //me retorna el id
        List<ReportDetail> reportDetails = mapToEntity(operationDTO.getItemDTOS(), summaryReport);
        reportDetailRepository.saveAll(reportDetails);

    }


    private List<ReportDetail> mapToEntity(List<OperationItemDTO> itemDTOS, SummaryReport summaryReport) {
        List<ReportDetail> reportDetails = new ArrayList<>();
        for (OperationItemDTO operationItemDTO : itemDTOS
        ) {
            Item item = itemRepository.getById(operationItemDTO.getId());
            if ("entrada".equals(summaryReport.getOperationType())) {
                item.setStock(item.getStock() + operationItemDTO.getCantidad());
            } else {
                item.setStock(item.getStock() - operationItemDTO.getCantidad());
            }
            itemRepository.save(item);

            ReportDetail reportDetail = new ReportDetail(operationItemDTO.getCantidad(), item, summaryReport);
            reportDetails.add(reportDetail);
        }
        return reportDetails;


        private SummaryReport mapToEntity (OperationDTO operationDTO){
            SummaryReport summaryReport = new SummaryReport(operationDTO.getOperationType(), operationDTO.getDate(),
                    operationDTO.getInvoiceAmount())
        }

        private List<Item> mapToEntity (List < ItemDTO > itemDTOS) {
        }
    }
}