package org.ada.inventorymanagementproject.controller;


import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.service.ItemService;
import org.ada.inventorymanagementproject.service.SummaryReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "summary-reports")
public class SummaryReportController {

    private final SummaryReportService summaryReportService;

    public SummaryReportController(SummaryReportService summaryReportService) {
        this.summaryReportService = summaryReportService;
    }

    @PostMapping
    public ResponseEntity create (@RequestBody SummaryReportDTO summaryReportDTO){
        summaryReportService.create((List<SummaryReportDTO>) summaryReportDTO);

        return new ResponseEntity<>(summaryReportDTO.getId() , HttpStatus.CREATED);
    }


    @DeleteMapping("/{summaryReportsId}")
    public ResponseEntity delete(@PathVariable Integer summaryReportsId) {
        summaryReportService.delete(summaryReportsId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
