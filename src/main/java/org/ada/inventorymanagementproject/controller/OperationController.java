package org.ada.inventorymanagementproject.controller;

import net.bytebuddy.build.Plugin;
import org.ada.inventorymanagementproject.dto.OperationDTO;
import org.ada.inventorymanagementproject.dto.SummaryReportDTO;
import org.ada.inventorymanagementproject.entity.Item;
import org.ada.inventorymanagementproject.service.ItemService;
import org.ada.inventorymanagementproject.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/operations")
public class OperationController {

   private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody OperationDTO operationDTO){

        operationService.create(operationDTO);

        return new ResponseEntity(operationDTO.getId(), HttpStatus.CREATED);
    }

  /*  @GetMapping
    public ResponseEntity retrieve(){

        return new ResponseEntity(operationService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{operationId}")
    public ResponseEntity retrieveById(@PathVariable Integer operationId){

        OperationDTO operationDTO = operationService.retrieveById(operationId);

        return new ResponseEntity(operationId HttpStatus.OK);

    }


    @PutMapping("/{summaryReportsId}")
    public ResponseEntity replace(@PathVariable Integer summaryReportId,
                                  @RequestBody SummaryReportDTO summaryReportDTO) {
        summaryReportService.replace(summaryReportId, summaryReportDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{supplierId}")
    public ResponseEntity modify(@PathVariable Integer summaryReportId,
                                 @RequestBody Map<String, Object> fieldsToModify) {
        summaryReportService.modify(summaryReportId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/{summaryReportsId}")
    public ResponseEntity delete(@PathVariable Integer summaryReportsId) {
        summaryReportService.delete(summaryReportsId);

        return new ResponseEntity(HttpStatus.OK);
    }*/
}
