package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Opportunity;





public interface OpportunityDAO extends GenericDAO<Opportunity, Integer> {
  
	List<Opportunity> findAll();
	






}


