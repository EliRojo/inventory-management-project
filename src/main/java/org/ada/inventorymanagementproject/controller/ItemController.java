package org.ada.inventorymanagementproject.controller;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ItemDTO itemDTO){

        itemService.create(itemDTO);

        return new ResponseEntity(itemDTO.getCode(), HttpStatus.CREATED); //CAMBIAR GETCODE POR MENSAJE "ITEM CREADO EXITOSAMENTE"
    }

    @GetMapping
    public ResponseEntity retrieve(){

        return new ResponseEntity(itemService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{itemCode}")
    public ResponseEntity retrieveById(@PathVariable String itemCode){

        ItemDTO itemDTO = itemService.retrieveByCode(itemCode);

        return new ResponseEntity(itemDTO, HttpStatus.OK);

    }
}
