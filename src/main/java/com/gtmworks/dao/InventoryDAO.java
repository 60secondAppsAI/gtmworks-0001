package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Inventory;





public interface InventoryDAO extends GenericDAO<Inventory, Integer> {
  
	List<Inventory> findAll();
	






}


