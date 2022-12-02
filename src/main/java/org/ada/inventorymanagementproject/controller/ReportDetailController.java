package org.ada.inventorymanagementproject.controller;

import org.ada.inventorymanagementproject.dto.ReportDetailDTO;
import org.ada.inventorymanagementproject.service.ReportDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/items/{itemId}/report-details")
public class ReportDetailController {

    private final ReportDetailService reportDetailService;

    public ReportDetailController(ReportDetailService reportDetailService) {
        this.reportDetailService = reportDetailService;
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
