package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.ItemDTO;
import org.ada.inventorymanagementproject.entity.Item;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ReportDetailService reportDetailService;

    public ItemService(ItemRepository itemRepository, ReportDetailService reportDetailService) {

        this.itemRepository = itemRepository;
        this.reportDetailService = reportDetailService;
    }

    public ItemDTO create(ItemDTO itemDTO){

        Item item = mapToEntity(itemDTO);
        checkForExistingItem(item.getCode());
        item = itemRepository.save(item);

        if(!CollectionUtils.isEmpty(itemDTO.getReportDetailDTOS())){
            reportDetailService.create(itemDTO.getReportDetailDTOS(), item);
        }

        return itemDTO;
    }


    public List<ItemDTO> retrieveAll(){ //
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(item -> mapToDTO(item))
                .collect(Collectors.toList());
    }

    public ItemDTO retrieveByCode(String code){ //
        Optional<Item> item = itemRepository.findById(code);

        if(item.isEmpty()){
            throw new ResourceNotFoundException("El código del item que está buscando no existe.");
        }

        return mapToDTO(item.get());
    }

    public List<ItemDTO> retrieveByName(String name){ //SI NO HAY NADA CREADO DEBE LANZAR UNA EXCEPCION

       List<Item> items = itemRepository.findByName(name);

        if(items.isEmpty()){
            throw new ResourceNotFoundException("El nombre del item que está buscando no existe.");
        }

        return items.stream()
                .map(item -> mapToDTO(item))
                .collect(Collectors.toList());
    }


    private void checkForExistingItem(String code) {
        if(itemRepository.existsById(code)){
            throw new ExistingResourceException();
        }
    }

    private ItemDTO mapToDTO(Item item) {

        ItemDTO itemDTO = new ItemDTO(item.getCode(), item.getName(), item.getStock(), item.getPrice(), item.getStatus(),
                item.getDescription(), reportDetailService.mapToDTOS(item.getReportDetails()));

        return itemDTO;
    }

    private Item mapToEntity(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.getCode(),itemDTO.getName(), itemDTO.getStock(), itemDTO.getPrice(),
                         itemDTO.getStatus(), itemDTO.getDescription());

        return item;
    }
}
