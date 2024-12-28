package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Solution;





public interface SolutionDAO extends GenericDAO<Solution, Integer> {
  
	List<Solution> findAll();
	






}


