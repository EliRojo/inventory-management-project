package org.ada.inventorymanagementproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ada.inventorymanagementproject.entity.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier,String> {
}
