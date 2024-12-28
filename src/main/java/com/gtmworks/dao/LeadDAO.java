package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Lead;





public interface LeadDAO extends GenericDAO<Lead, Integer> {
  
	List<Lead> findAll();
	






}


