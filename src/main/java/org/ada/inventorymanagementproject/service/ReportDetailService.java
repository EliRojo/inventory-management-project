package org.ada.inventorymanagementproject.service;

import org.ada.inventorymanagementproject.dto.ReportDetailDTO;

import org.ada.inventorymanagementproject.entity.Item;
import org.ada.inventorymanagementproject.entity.ReportDetail;
import org.ada.inventorymanagementproject.exceptions.ExistingResourceException;
import org.ada.inventorymanagementproject.exceptions.ResourceNotFoundException;
import org.ada.inventorymanagementproject.repository.ItemRepository;
import org.ada.inventorymanagementproject.repository.ReportDetailRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportDetailService {

    private final ReportDetailRepository reportDetailRepository;
    private final ItemRepository itemRepository;

    public ReportDetailService(ReportDetailRepository reportDetailRepository, ItemRepository itemRepository) {
        this.reportDetailRepository = reportDetailRepository;
        this.itemRepository = itemRepository;
    }

    public void create(ReportDetailDTO reportDetailDTO, String itemId){

        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isEmpty()){
            throw new ResourceNotFoundException("El item al que desea asociarle un detalle de reporte, no existe.");
        }
        ReportDetail reportDetail = mapToEntity(reportDetailDTO, item.get());
        reportDetail = reportDetailRepository.save(reportDetail);
        reportDetailDTO.setId(reportDetail.getId());
    }

    public void create(List<ReportDetailDTO> reportDetailDTOS, Item item){ //Crea el reporte desde el recurso Item
        List<ReportDetail> reportDetails = reportDetailDTOS.stream()
                .map(reportDetailDTO -> mapToEntity(reportDetailDTO, item))
                .collect(Collectors.toList());
        reportDetailRepository.saveAll(reportDetails);
    }


    public ReportDetailDTO retrieveById(String itemId, Integer reportDetailId){

        if(!itemRepository.existsById(itemId)){
            throw new ResourceNotFoundException("El código del item que está buscando no existe.");
        }

        Optional<ReportDetail> reportDetail = reportDetailRepository.findById(reportDetailId);

        if(reportDetail.isEmpty()){
            throw new ResourceNotFoundException("El id del reporte detalle que está buscando no existe.");
        }

        return mapToDTO(reportDetail.get());
    }

    public List<ReportDetailDTO> mapToDTOS(List<ReportDetail> reportDetails){

        return reportDetails.stream()
                .map(reportDetail -> mapToDTO(reportDetail))
                .collect(Collectors.toList());

    }

    private ReportDetail mapToEntity(ReportDetailDTO reportDetailDTO, Item item) {

        ReportDetail reportDetail = new ReportDetail(reportDetailDTO.getQuantity(), item);
        return reportDetail;
    }

    private ReportDetailDTO mapToDTO(ReportDetail reportDetail){

        ReportDetailDTO reportDetailDTO = new ReportDetailDTO(reportDetail.getQuantity());
        reportDetailDTO.setId(reportDetail.getId());
        return reportDetailDTO;
    }

}
