package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.SupplierRepository;
import org.ada.inventorymanagementproject.entity.Supplier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    public final SummaryReportService summaryReportService;


    public SupplierService(SupplierRepository supplierRepository,
                           SummaryReportService summaryReportService) {
        this.supplierRepository = supplierRepository;
        this.summaryReportService = summaryReportService;
    }

    public SupplierDTO create(SupplierDTO supplierDTO){
        Supplier supplier = mapToEntity(supplierDTO);
        checkForExistingSupplier(supplier.getId());
        supplier = supplierRepository.save(supplier);
        return supplierDTO;

    }


    public List<SupplierDTO> retrieveAll(){
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplier -> mapToDTO(supplier))
                .collect(Collectors.toList());
    }

    public SupplierDTO retrieveById (Integer supplierId){
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);

        if(supplier.isEmpty()){
            throw new ResourceNotFoundException();
        }

        return mapToDTO(supplier.get());
    }

    private void checkForExistingSupplier(Integer supplierId) {
        if(supplierRepository.existsById(supplierId)){
            throw new ExistingResourceException();
        }
    }

    public void delete(Integer supplierId) {
        try {
            supplierRepository.deleteById(supplierId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
        }
    }

    public void replace(Integer supplierId, SupplierDTO supplierDTO) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        Supplier supplierToReplace = supplier.get();
        supplierToReplace.setCompany(supplierDTO.getCompany());
        supplierToReplace.setAddress(supplierDTO.getAddres());
        supplierToReplace.setContact(supplierToReplace.getContact());
        supplierToReplace.setStatus(supplierDTO.getStatus());

        supplierRepository.save(supplierToReplace);
    }

    public void modify(Integer supplierId, Map<String, Object> fieldsToModify) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        Supplier supplierToModify = supplier.get();
        fieldsToModify.forEach((key, value) -> supplierToModify.modifyAttributeValue(key, value));
        supplierRepository.save(supplierToModify);
    }


    private SupplierDTO mapToDTO(Supplier supplier) {

        SupplierDTO supplierDTO = new SupplierDTO(supplier.getCompany(),supplier.getAddress(),
                supplier.getContact(),supplier.getStatus(), summaryReportService.mapToDTOS(supplier.getSummaryReports()));//le paso la lista para que la mapee

        supplierDTO.setId(supplier.getId());

        return supplierDTO;
    }

    private Supplier mapToEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier(supplierDTO.getId(), supplierDTO.getCompany(),
                supplierDTO.getContact(), supplierDTO.getAddres(),
                supplierDTO.getStatus(), null);

        return supplier;
    }
}
