package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Invoice;





public interface InvoiceDAO extends GenericDAO<Invoice, Integer> {
  
	List<Invoice> findAll();
	






}


