package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.SupplierRepository;
import org.ada.inventorymanagementproject.entity.Supplier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;


import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ItemService itemService;


    public SupplierService(SupplierRepository supplierRepository,
                           ItemService itemService) {
        this.supplierRepository = supplierRepository;
        this.itemService = itemService;
    }

    public SupplierDTO create(SupplierDTO supplierDTO) {

        Supplier supplier = mapToEntity(supplierDTO);
        checkForExistingSupplier(supplier.getId());
        supplier = supplierRepository.save(supplier);

        return supplierDTO;

    }



    public List<SupplierDTO> retrieveAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplier -> mapToDTO(supplier))
                .collect(Collectors.toList());
    }

    public SupplierDTO retrieveById(String supplierId) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);

        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException("El id del proveedor que está buscando no existe.");
        }

        return mapToDTO(supplier.get());
    }

    public SupplierDTO retrieveByIdWithItems(String supplierId){
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);

        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException("El id del proveedor que está buscando no existe.");
        }

        return mapToDTOWithItems(supplier.get());
    }


    public void delete(String supplierId) {
        try {
            supplierRepository.deleteById(supplierId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No se pudo eliminar el proveedor porque el id que está ingresando no existe.");
        }
    }

    public void replace(String supplierId, SupplierDTO supplierDTO) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        Supplier supplierToReplace = supplier.get();
        supplierToReplace.setCompany(supplierDTO.getCompany());
        supplierToReplace.setAddress(supplierDTO.getAddress());
        supplierToReplace.setContact(supplierDTO.getContact());
        supplierToReplace.setStatus(supplierDTO.getStatus());

        supplierRepository.save(supplierToReplace);
    }

    public void modify(String supplierId, Map<String, Object> fieldsToModify) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        Supplier supplierToModify = supplier.get();
        fieldsToModify.forEach((key, value) -> supplierToModify.modifyAttributeValue(key, value));
        supplierRepository.save(supplierToModify);
    }



    private Supplier mapToEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier(supplierDTO.getId(), supplierDTO.getCompany(),
                supplierDTO.getAddress(), supplierDTO.getContact(), supplierDTO.getStatus());

        return supplier;
    }

    private SupplierDTO mapToDTO(Supplier supplier) {

        SupplierDTO supplierDTO = new SupplierDTO(supplier.getId(), supplier.getCompany(), supplier.getAddress(),
                supplier.getContact(), supplier.getStatus());

        return supplierDTO;
    }

    private SupplierDTO mapToDTOWithItems(Supplier supplier) {

        SupplierDTO supplierDTO = new SupplierDTO(supplier.getId(), supplier.getCompany(), supplier.getAddress(),
                supplier.getContact(), supplier.getStatus(), itemService.mapToDTOS(supplier.getItems()));

        return supplierDTO;
    }


    private void checkForExistingSupplier(String supplierId) {
        if (supplierRepository.existsById(supplierId)) {
            throw new ExistingResourceException("El proveedor que está intentando crear ya existe.");
        }
    }

}

