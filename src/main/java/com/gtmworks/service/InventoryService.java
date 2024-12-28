package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Inventory;
import com.gtmworks.dto.InventoryDTO;
import com.gtmworks.dto.InventorySearchDTO;
import com.gtmworks.dto.InventoryPageDTO;
import com.gtmworks.dto.InventoryConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InventoryService extends GenericService<Inventory, Integer> {

	List<Inventory> findAll();

	ResultDTO addInventory(InventoryDTO inventoryDTO, RequestDTO requestDTO);

	ResultDTO updateInventory(InventoryDTO inventoryDTO, RequestDTO requestDTO);

    Page<Inventory> getAllInventorys(Pageable pageable);

    Page<Inventory> getAllInventorys(Specification<Inventory> spec, Pageable pageable);

	ResponseEntity<InventoryPageDTO> getInventorys(InventorySearchDTO inventorySearchDTO);
	
	List<InventoryDTO> convertInventorysToInventoryDTOs(List<Inventory> inventorys, InventoryConvertCriteriaDTO convertCriteria);

	InventoryDTO getInventoryDTOById(Integer inventoryId);







}





