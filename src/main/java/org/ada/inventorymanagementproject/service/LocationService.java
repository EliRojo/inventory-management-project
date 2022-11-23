package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.LocationDTO;
import org.ada.inventorymanagementproject.entity.Item;
import org.ada.inventorymanagementproject.entity.Location;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public LocationDTO create(LocationDTO locationDTO){
        Location location = mapToEntity(locationDTO);
        checkForExistingLocation(location.getId());
        location = locationRepository.save(location);

        return locationDTO;
    }
    public List<LocationDTO> retrieveAll(){ //
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(location -> mapToDTO(location))
                .collect(Collectors.toList());
    }
    public LocationDTO retrieveByCode(Integer id){ //
        Optional<Location> location = locationRepository.findById(id);

        if(location.isEmpty()){
            throw new ResourceNotFoundException();
        }

        return mapToDTO(location.get());
    }

    public List<LocationDTO> retrieveById(Integer id){

        Optional<Location> locations = locationRepository.findById(id);

        if(locations.isEmpty()){
            throw new ResourceNotFoundException();
        }

        return locations.stream()
                .map(location -> mapToDTO(location))
                .collect(Collectors.toList());
    }
    private void checkForExistingLocation(Integer id) {
        if(locationRepository.existsById(id)){
            throw new ExistingResourceException();
        }
    }
    private LocationDTO mapToDTO(Location location) {

        LocationDTO locationDTO = new LocationDTO(location.getId(),location.getSection(),location.getColumn(),location.getRow());

        return locationDTO;
    }
    private Location mapToEntity(LocationDTO locationDTO) {
        Location location = new Location(locationDTO.getId(),locationDTO.getSection(),locationDTO.getColumn(),locationDTO.getRow());

        return location;
    }
}
