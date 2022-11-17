package org.ada.inventorymanagementproject.repository;

import org.ada.inventorymanagementproject.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
