package org.ada.inventorymanagementproject.controller;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.ReportDetailDTO;
import org.ada.inventorymanagementproject.service.ReportDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items/{itemId}/summary-reports/{summaryReportId}/report-details")
public class ReportDetailController {

    private final ReportDetailService reportDetailService;

    public ReportDetailController(ReportDetailService reportDetailService) {
        this.reportDetailService = reportDetailService;
    }

    //Debemos cambiar la LOGICA PARA QUE TAMBIEN ME CREE EL SUMMARY REPORT
    @PostMapping
    public ResponseEntity create(@PathVariable String itemId,
                                 @PathVariable String summaryReportId,
                                 @RequestBody ReportDetailDTO reportDetailDTO){

        reportDetailService.create(reportDetailDTO, itemId);

        return new ResponseEntity(reportDetailDTO.getId(), HttpStatus.CREATED);
    }

    //NO QUIERO QUE ME TRAIGA TODO SOLO EL NOMBRE DEL ITEM
    @GetMapping
    public ResponseEntity retrieveById(@PathVariable String itemId,
                                       @PathVariable Integer reportDetailId){

        ReportDetailDTO reportDetailDTO = reportDetailService.retrieveById(itemId, reportDetailId);

        return  new ResponseEntity(reportDetailDTO, HttpStatus.OK);
    }




}
