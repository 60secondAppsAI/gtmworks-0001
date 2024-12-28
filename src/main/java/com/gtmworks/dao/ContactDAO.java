package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Contact;





public interface ContactDAO extends GenericDAO<Contact, Integer> {
  
	List<Contact> findAll();
	






}


