package org.ada.inventorymanagementproject.repository;

import org.ada.inventorymanagementproject.controller.OperationController;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<OperationController,Integer> {
}
