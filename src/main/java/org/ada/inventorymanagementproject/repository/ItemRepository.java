package org.ada.inventorymanagementproject.repository;

import org.ada.inventorymanagementproject.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {

     List<Item> findByName(String name);
}
