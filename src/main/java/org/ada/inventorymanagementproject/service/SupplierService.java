package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.SupplierRepository;
import org.ada.inventorymanagementproject.entity.Supplier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
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
        if (!CollectionUtils.isEmpty(supplierDTO.getItemDTOS()))
            itemService.create(supplierDTO.getItemDTOS(), supplier);
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
            throw new ResourceNotFoundException();
        }

        return mapToDTO(supplier.get());
    }


    public void delete(String supplierId) {
        try {
            supplierRepository.deleteById(supplierId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
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
        supplierToReplace.setContact(supplierToReplace.getContact());
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
                supplier.getContact(), supplier.getStatus(), itemService.mapToDTOS(supplier.getItems()));//le paso la lista para que la mapee


        return supplierDTO;
    }


    private void checkForExistingSupplier(String supplierId) {
        if (supplierRepository.existsById(supplierId)) {
            throw new ExistingResourceException();
        }
    }

}

