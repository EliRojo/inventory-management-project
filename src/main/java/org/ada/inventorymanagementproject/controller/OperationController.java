package org.ada.inventorymanagementproject.controller;

import org.ada.inventorymanagementproject.dto.OperationDTO;
import org.ada.inventorymanagementproject.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
