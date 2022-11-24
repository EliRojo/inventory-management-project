package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.SupplierRepository;
import org.ada.inventorymanagementproject.entity.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;
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

    private SupplierDTO mapToDTO(Supplier supplier) {

        SupplierDTO supplierDTO = new SupplierDTO(supplier.getId(),supplier.getCompany(),supplier.getDirection(),
                supplier.getContact(),supplier.getStatus(), summaryReportService.mapToDTOS(supplier.getSummaryReports())); //le paso la lista para que la mapee

        return supplierDTO;
    }

    private Supplier mapToEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier(supplierDTO.getId(), supplierDTO.getCompany(),
                supplierDTO.getContact(), supplierDTO.getDirection(),
                supplierDTO.getStatus(), null);

        return supplier;
    }
}
