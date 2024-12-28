package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Team;





public interface TeamDAO extends GenericDAO<Team, Integer> {
  
	List<Team> findAll();
	






}


