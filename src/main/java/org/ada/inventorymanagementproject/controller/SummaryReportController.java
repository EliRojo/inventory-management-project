package org.ada.inventorymanagementproject.controller;


import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.service.ItemService;
import org.ada.inventorymanagementproject.service.SummaryReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping
    public ResponseEntity retrieve(){

        return new ResponseEntity(summaryReportService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{summaryReportId}")
    public ResponseEntity retrieveById(@PathVariable Integer summaryReportId){

        SummaryReportDTO summaryReportDTO = summaryReportService.retrieveById(summaryReportId);

        return new ResponseEntity(summaryReportDTO, HttpStatus.OK);

    }


    @PutMapping("/{summaryReportId}")
    public ResponseEntity replace(@PathVariable Integer summaryReportId,
                                  @RequestBody SummaryReportDTO summaryReportDTO) {
        summaryReportService.replace(summaryReportId, summaryReportDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{summaryReportId}")
    public ResponseEntity modify(@PathVariable Integer summaryReportId,
                                 @RequestBody Map<String, Object> fieldsToModify) {
        summaryReportService.modify(summaryReportId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/{summaryReportId}")
    public ResponseEntity delete(@PathVariable Integer summaryReportsId) {
        summaryReportService.delete(summaryReportsId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
