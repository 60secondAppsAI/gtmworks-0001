package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Account;





public interface AccountDAO extends GenericDAO<Account, Integer> {
  
	List<Account> findAll();
	






}


