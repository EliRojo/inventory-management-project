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
@RequestMapping(path = "/summary-reports/{summaryReportId}/report-details")
public class ReportDetailController {

    private final ReportDetailService reportDetailService;

    public ReportDetailController(ReportDetailService reportDetailService) {
        this.reportDetailService = reportDetailService;
    }

    @GetMapping("/{reportDetailId}")
    public ResponseEntity retrieveById(@PathVariable String summaryReportId,
                                       @PathVariable Integer reportDetailId){

        ReportDetailDTO reportDetailDTO = reportDetailService.retrieveById(summaryReportId, reportDetailId);

        return new ResponseEntity(reportDetailDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{reportDetailId}")
    public ResponseEntity delete(@PathVariable Integer reportDetailId) {
        reportDetailService.delete(reportDetailId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{reportDetailId}")
    public ResponseEntity replace(@PathVariable String summaryReportId,
                                  @PathVariable Integer reportDetailId,
                                  @RequestBody ReportDetailDTO reportDetailDTO) {

        reportDetailService.replace(summaryReportId, reportDetailId, reportDetailDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{reportDetailId}")
    public ResponseEntity modify(@PathVariable String summaryReportId,
                                 @PathVariable Integer reportDetailId,
                                 @RequestBody Map<String, Object> fieldsToModify) {

        reportDetailService.modify(summaryReportId, reportDetailId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
}
