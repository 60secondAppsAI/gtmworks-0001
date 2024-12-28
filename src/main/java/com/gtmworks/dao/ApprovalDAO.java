package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Approval;





public interface ApprovalDAO extends GenericDAO<Approval, Integer> {
  
	List<Approval> findAll();
	






}


