package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Case;





public interface CaseDAO extends GenericDAO<Case, Integer> {
  
	List<Case> findAll();
	






}


