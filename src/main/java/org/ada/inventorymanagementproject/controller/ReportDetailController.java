package org.ada.inventorymanagementproject.controller;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.ReportDetailDTO;
import org.ada.inventorymanagementproject.service.ReportDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/report-detail")
public class ReportDetailController {
    private final ReportDetailService reportDetailService;

    public ReportDetailController(ReportDetailService reportDetailService) {
        this.reportDetailService = reportDetailService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ReportDetailDTO reportDetailDTO){
        reportDetailService.create(reportDetailDTO);

        return new ResponseEntity(reportDetailDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){

        return new ResponseEntity(reportDetailService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{reportDetailId}")
    public ResponseEntity retrieveById(@PathVariable Integer reportDetailId){

        ReportDetailDTO reportDetailDTO = reportDetailService.retrieveByCode(reportDetailId);

        return new ResponseEntity(reportDetailDTO, HttpStatus.OK);

    }
}
