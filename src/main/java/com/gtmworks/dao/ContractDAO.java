package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Contract;





public interface ContractDAO extends GenericDAO<Contract, Integer> {
  
	List<Contract> findAll();
	






}


