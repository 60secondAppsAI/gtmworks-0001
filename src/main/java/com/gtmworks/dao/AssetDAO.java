package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Asset;





public interface AssetDAO extends GenericDAO<Asset, Integer> {
  
	List<Asset> findAll();
	






}


