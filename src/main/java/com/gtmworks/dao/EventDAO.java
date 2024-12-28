package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Event;





public interface EventDAO extends GenericDAO<Event, Integer> {
  
	List<Event> findAll();
	






}


