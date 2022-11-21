package org.ada.inventorymanagementproject.controller;



import org.ada.inventorymanagementproject.dto.LocationDTO;
import org.ada.inventorymanagementproject.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @PostMapping
    public ResponseEntity create(@RequestBody LocationDTO locationDTO){

        locationService.create(locationDTO);

        return new ResponseEntity(locationDTO.getId(), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity retrieve(){

        return new ResponseEntity(locationService.retrieveAll(), HttpStatus.OK);
    }
    @GetMapping("/{locationId}")
    public ResponseEntity retrieveById(@PathVariable Integer locationId){

        LocationDTO locationDTO = (LocationDTO) locationService.retrieveById(locationId);

        return new ResponseEntity(locationDTO, HttpStatus.OK);

    }
}
