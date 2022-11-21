package org.ada.inventorymanagementproject.controller;


import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.service.ItemService;
import org.ada.inventorymanagementproject.service.SummaryReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/SummaryReport")
public class SummaryReportController {

    private final SummaryReportService summaryReportService;

    public SummaryReportController(SummaryReportService summaryReportService) {
        this.summaryReportService = summaryReportService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody SummaryReportDTO summaryReportDTO){

        SummaryReportDTO createdSummaryReportDTO = summaryReportService.create(summaryReportDTO);

        return new ResponseEntity(summaryReportDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){

        return new ResponseEntity(summaryReportService.retrieveAll(), HttpStatus.OK);
    }


    @GetMapping("/{SummaryReportId}")
    public ResponseEntity retrieveById(@PathVariable Integer summaryReportId){

        SummaryReportDTO SummaryReportDTO = summaryReportService.retrieveById(summaryReportId);

        return new ResponseEntity(SummaryReportDTO, HttpStatus.OK);

    }
}
