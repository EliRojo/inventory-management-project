package org.ada.inventorymanagementproject.controller;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity create (@RequestBody SupplierDTO supplierDTO){

        SupplierDTO createdSupplierDTO = supplierService.create(supplierDTO);
        return new ResponseEntity(supplierDTO.getId(), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity retrieve(){

        return new ResponseEntity(supplierService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity retrieveById(@PathVariable Integer supplierId){

        SupplierDTO supplierDTO = supplierService.retrieveById(supplierId);

        return new ResponseEntity(supplierDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity delete(@PathVariable Integer supplierId) {
        supplierService.delete(supplierId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity replace(@PathVariable Integer supplierId,
                                  @RequestBody SupplierDTO supplierDTO) {
        supplierService.replace(supplierId, supplierDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{supplierId}")
    public ResponseEntity modify(@PathVariable Integer supplierId,
                                 @RequestBody Map<String, Object> fieldsToModify) {
        supplierService.modify(supplierId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
}
