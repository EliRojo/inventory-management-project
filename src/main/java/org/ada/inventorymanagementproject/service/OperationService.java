package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.OperationDTO;
import org.ada.inventorymanagementproject.dto.OperationItemDTO;
import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.entity.Item;
import org.ada.inventorymanagementproject.entity.ReportDetail;
import org.ada.inventorymanagementproject.entity.SummaryReport;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.ItemRepository;
import org.ada.inventorymanagementproject.repository.ReportDetailRepository;
import org.ada.inventorymanagementproject.repository.SummaryReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperationService {

    private final SummaryReportRepository summaryReportRepository;
    private final SummaryReport summaryReport;
    private final SummaryReportService summaryReportService;
    private final ItemRepository itemRepository;
    private final ReportDetailRepository reportDetailRepository;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public OperationService(SummaryReportRepository summaryReportRepository, SummaryReport summaryReport, SummaryReportService summaryReportService,
                            ItemRepository itemRepository, ReportDetailRepository reportDetailRepository) {
        this.summaryReportRepository = summaryReportRepository;
        this.summaryReport = summaryReport;
        this.summaryReportService = summaryReportService;
        this.itemRepository = itemRepository;
        this.reportDetailRepository = reportDetailRepository;
    }

    public void create(OperationDTO operationDTO) {

            for(OperationItemDTO operationItemDTO : operationDTO.getItemDTOS()){
                checkForExistingItem(operationItemDTO.getCode());
            }

            SummaryReport summaryReport = mapToEntity(operationDTO);
            checkForExistingSummaryReport(summaryReport.getSummaryId());
            summaryReport = summaryReportRepository.save(summaryReport);

            List<ReportDetail> reportDetails = mapToEntity(operationDTO.getItemDTOS(), summaryReport);
            reportDetailRepository.saveAll(reportDetails);

        }

    private void checkForExistingItem(String code) {
        if (itemRepository.existsById(code)) {
            throw new ResourceNotFoundException();
        }
    }


    private void checkForExistingSummaryReport(String summaryReportId) {
        if (summaryReportRepository.existsById(summaryReportId)) {
            throw new ExistingResourceException("El Summary Report que está intentando crear ya existe.");
        }
    }


    private List<ReportDetail> mapToEntity(List<OperationItemDTO> itemDTOS, SummaryReport summaryReport) {

        List<ReportDetail> reportDetails = new ArrayList<>();
        for (OperationItemDTO operationItemDTO : itemDTOS) {
            Item item = itemRepository.getById(operationItemDTO.getCode());

            stockMovementOperation(operationItemDTO, summaryReport.getOperationType(), item);

           /* if ("entrada".equals(summaryReport.getOperationType())) {
                item.setStock(item.getStock() + operationItemDTO.getQuantity());
            } else {
                item.setStock(item.getStock() - operationItemDTO.getQuantity());
            }*/
            itemRepository.save(item);

            ReportDetail reportDetail = new ReportDetail(operationItemDTO.getQuantity(), item, summaryReport);
            reportDetails.add(reportDetail);
        }
        return reportDetails;
    }

    private void stockMovementOperation(OperationItemDTO operationItemDTO, String operationType, Item item) {

        if ("entrada".equals(operationType)) {
            item.setStock(item.getStock() + operationItemDTO.getQuantity());
        } else if ("salida".equals(operationType)) {
            item.setStock(item.getStock() - operationItemDTO.getQuantity());
        } else{
            throw new ResourceNotFoundException();
        }

    }

    private SummaryReport mapToEntity(OperationDTO operationDTO) {

        SummaryReport summaryReport = new SummaryReport(operationDTO.getSummaryId(), operationDTO.getOperationType(),
                LocalDate.parse(operationDTO.getDate(), DATE_TIME_FORMATTER), operationDTO.getInvoiceAmount());

        return summaryReport;

    }

    private List<Item> mapToEntity(List<ItemDTO> itemDTOS) {

        return itemDTOS.stream()
                .map(itemDTO -> mapToEntity(itemDTO))
                .collect(Collectors.toList());
    }

    private Item mapToEntity(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.getCode(), itemDTO.getName(), itemDTO.getStock(), itemDTO.getPrice(),
                itemDTO.getStatus(), itemDTO.getDescription());

        return item;
    }

}