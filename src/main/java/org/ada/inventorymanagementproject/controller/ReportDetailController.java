package org.ada.inventorymanagementproject.controller;

import org.ada.inventorymanagementproject.dto.ReportDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/report-detail")
public class ReportDetailController {

    @PostMapping
    public ResponseEntity create(@RequestBody ReportDetailDTO reportDetailDTO){

        return new ResponseEntity(reportDetailDTO())
    }
}
