package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Task;





public interface TaskDAO extends GenericDAO<Task, Integer> {
  
	List<Task> findAll();
	






}


