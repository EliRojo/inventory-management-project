package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.SupplierRepository;
import org.ada.inventorymanagementproject.entity.Supplier;
import java.util.List;
import java.util.Optional;


import java.util.stream.Collectors;

public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierDTO create(SupplierDTO supplierDTO){
        Supplier supplier = mapToEntity(supplierDTO);
        checkForExistingSupplier(supplier.getSupplierCode());
        supplier = supplierRepository.save(supplier);
        return supplierDTO;

    }



    public List<SupplierDTO> retrieveAll(){
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplier -> mapToDTO(supplier))
                .collect(Collectors.toList());
    }

    public SupplierDTO retrieveByCode(String supplierCode){
        Optional<Supplier> supplier = supplierRepository.findById(supplierCode);

        if(supplier.isEmpty()){
            throw new ResourceNotFoundException();
        }

        return mapToDTO(supplier.get());
    }

    private void checkForExistingSupplier(String supplierCode) {
        if(supplierRepository.existsById(supplierCode)){
            throw new ExistingResourceException();
        }
    }

    private SupplierDTO mapToDTO(Supplier supplier) {

        SupplierDTO supplierDTO = new SupplierDTO(supplier.getSupplierCode(),supplier.getCompany(),supplier.getDirection(),supplier.getContact(),supplier.getStatus());

        return supplierDTO;
    }

    private Supplier mapToEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier(supplierDTO.getSupplierCode(), supplierDTO.getCompany(), supplierDTO.getContact(), supplierDTO.getDirection(),
                supplierDTO.getStatus());

        return supplier;
    }
}
