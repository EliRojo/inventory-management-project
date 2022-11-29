package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.entity.SummaryReport;
import org.ada.inventorymanagementproject.repository.ItemRepository;
import org.ada.inventorymanagementproject.repository.SummaryReportRepository;

public class OperationService {
    private final SummaryReportRepository summaryReportRepository;
    private final SummaryReport summaryReport;
    private final SummaryReportService summaryReportService;
    private final ItemRepository itemRepository;

    public OperationService(SummaryReportRepository summaryReportRepository, SummaryReport summaryReport, SummaryReportService summaryReportService, ItemRepository itemRepository) {
        this.summaryReportRepository = summaryReportRepository;
        this.summaryReport = summaryReport;
        this.summaryReportService = summaryReportService;
        this.itemRepository = itemRepository;
    }

    



    /*public void create(OperationDTO operationDTO, SummaryReportDTO summaryReportDTO) {
        SummaryReport summaryReport = mapToEntity(summaryReportDTO);
        checkForExistingSummaryReport(summaryReport.getId());

        summaryReport = summaryReportRepository.save(summaryReport);

        if (!CollectionUtils.isEmpty(operationDTO.getSummaryReportDTOS())) {
            summaryReportService.create(operationDTO.getSummaryReportDTOS());
        }




    }

    private void checkForExistingSummaryReport(Integer id) {
    }*/


}
