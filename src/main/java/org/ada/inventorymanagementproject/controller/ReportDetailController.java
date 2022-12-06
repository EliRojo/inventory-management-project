package org.ada.inventorymanagementproject.controller;

import org.ada.inventorymanagementproject.dto.ReportDetailDTO;
import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.service.ReportDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/items/{itemCode}/report-details")
public class ReportDetailController {

    private final ReportDetailService reportDetailService;

    public ReportDetailController(ReportDetailService reportDetailService) {
        this.reportDetailService = reportDetailService;
    }

    @GetMapping("/{reportDetailId}")
    public ResponseEntity retrieveById(@PathVariable String itemCode,
                                       @PathVariable Integer reportDetailId){

        ReportDetailDTO reportDetailDTO = reportDetailService.retrieveById(itemCode, reportDetailId);

        return new ResponseEntity(reportDetailDTO, HttpStatus.OK);

    }


    @PutMapping("/{reportDetailId}")
    public ResponseEntity replace(@PathVariable Integer reportDetailId,
                                  @RequestBody ReportDetailDTO reportDetailDTO) {

        reportDetailService.replace(reportDetailId, reportDetailDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{reportDetailId}")
    public ResponseEntity modify(@PathVariable Integer reportDetailId,
                                 @RequestBody Map<String, Object> fieldsToModify) {

        reportDetailService.modify(reportDetailId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
}
