package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.dto.ReportDetailDTO;
import org.ada.inventorymanagementproject.dto.SupplierDTO;
import org.ada.inventorymanagementproject.entity.Item;
import org.ada.inventorymanagementproject.entity.ReportDetail;
import org.ada.inventorymanagementproject.entity.Supplier;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.ItemRepository;
import org.ada.inventorymanagementproject.repository.SupplierRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ReportDetailService reportDetailService;
    private final SupplierRepository supplierRepository;

    public ItemService(ItemRepository itemRepository, ReportDetailService reportDetailService, SupplierRepository supplierRepository) {

        this.itemRepository = itemRepository;
        this.reportDetailService = reportDetailService;
        this.supplierRepository = supplierRepository;
    }


    public void create(ItemDTO itemDTO, String supplierId) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException("El proveedor al que está intentando asociar no existe.");
        }

        Item item = mapToEntity(itemDTO, supplier.get());
        checkForExistingItem(item.getCode());
        item = itemRepository.save(item);
        itemDTO.setCode(item.getCode());

    }

    public List<ItemDTO> retrieveAll() {

        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(item -> mapToDTO(item))
                .collect(Collectors.toList());
    }


    public ItemDTO retrieveByCode(String code) {
        Optional<Item> item = itemRepository.findById(code);

        if (item.isEmpty()) {
            throw new ResourceNotFoundException("El código del item que está buscando no existe.");
        }

        return mapToDTO(item.get());
    }



    public void delete(String itemCode) {
        try {
            itemRepository.deleteById(itemCode);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
        }
    }

    public void replace(String supplierId, String personId,  ItemDTO itemDTO) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException("El id del proveedor que está ingresando no existe.");
        }

        Optional<Item> item = itemRepository.findById(personId);
        if (item.isEmpty()) {
            throw new ResourceNotFoundException("El código del item que está ingresando no existe.");
        }

        Item personToReplace = item.get();
        personToReplace.setName(itemDTO.getName());
        personToReplace.setStock(itemDTO.getStock());
        personToReplace.setPrice(itemDTO.getPrice());
        personToReplace.setStatus(itemDTO.getStatus());
        personToReplace.setDescription(itemDTO.getDescription());
        itemRepository.save(personToReplace);
    }

    public void modify(String supplierId, String itemCode, Map<String, Object> fieldsToModify) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException("El id del proveedor que está ingresando no existe.");
        }

        Optional<Item> item = itemRepository.findById(itemCode);
        if (item.isEmpty()) {
            throw new ResourceNotFoundException("El código del item que está ingresando no existe.");
        }
        Item itemToModify = item.get();
        fieldsToModify.forEach((key, value) -> itemToModify.modifyAttributeValue(key, value));
        itemRepository.save(itemToModify);
    }


    public List<ItemDTO> mapToDTOS(List<Item> items) {

        return items.stream()
                .map(item -> mapToDTO(item))
                .collect(Collectors.toList());
    }
    private void checkForExistingItem(String code) {
        if (itemRepository.existsById(code)) {
            throw new ExistingResourceException("El item que está intentando crear ya existe.");
        }
    }

    private ItemDTO mapToDTO(Item item) {

        ItemDTO itemDTO = new ItemDTO(item.getCode(), item.getName(), item.getStock(), item.getPrice(), item.getStatus(),
                item.getDescription());

        return itemDTO;
    }

    private Item mapToEntity(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.getCode(), itemDTO.getName(), itemDTO.getStock(), itemDTO.getPrice(),
                itemDTO.getStatus(), itemDTO.getDescription());

        return item;
    }

    private Item mapToEntity(ItemDTO itemDTO, Supplier supplier) {
        Item item = new Item(itemDTO.getCode(), itemDTO.getName(), itemDTO.getStock(), itemDTO.getPrice(),
                itemDTO.getStatus(), itemDTO.getDescription(), supplier);

        return item;
    }




}