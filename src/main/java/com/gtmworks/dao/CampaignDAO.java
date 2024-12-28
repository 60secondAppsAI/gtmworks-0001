package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Campaign;





public interface CampaignDAO extends GenericDAO<Campaign, Integer> {
  
	List<Campaign> findAll();
	






}


