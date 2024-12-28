package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Revenue;





public interface RevenueDAO extends GenericDAO<Revenue, Integer> {
  
	List<Revenue> findAll();
	






}


