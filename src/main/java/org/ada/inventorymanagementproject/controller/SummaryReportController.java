package org.ada.inventorymanagementproject.controller;


import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.service.ItemService;
import org.ada.inventorymanagementproject.service.SummaryReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/suppliers/{supplierId}/summary-reports")
public class SummaryReportController {

    private final SummaryReportService summaryReportService;

    public SummaryReportController(SummaryReportService summaryReportService) {
        this.summaryReportService = summaryReportService;
    }

    @PostMapping
    public ResponseEntity create (@PathVariable Integer supplierId ,
                                   @RequestBody SummaryReportDTO summaryReportDTO){
        summaryReportService.create(summaryReportDTO , supplierId);

        return new ResponseEntity<>(summaryReportDTO.getId() , HttpStatus.CREATED);
    }

    @GetMapping("/{summaryReportId}")
    public ResponseEntity retrieveById(@PathVariable Integer supplierId,
                                       @PathVariable Integer summaryReportsId){
        SummaryReportDTO summaryReportDTO = summaryReportService.retrieveById(supplierId, summaryReportsId);

        return new ResponseEntity(summaryReportDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{summaryReportId}")
    public ResponseEntity delete(@PathVariable Integer summaryReportsId) {
        summaryReportService.delete(summaryReportsId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
