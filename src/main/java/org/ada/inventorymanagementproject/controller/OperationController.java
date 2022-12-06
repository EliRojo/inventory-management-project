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

        return new ResponseEntity(operationDTO.getSummaryId(), HttpStatus.CREATED);
    }

}
