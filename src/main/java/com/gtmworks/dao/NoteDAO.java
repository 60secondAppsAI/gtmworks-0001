package com.gtmworks.dao;

import java.util.List;

import com.gtmworks.dao.GenericDAO;
import com.gtmworks.domain.Note;





public interface NoteDAO extends GenericDAO<Note, Integer> {
  
	List<Note> findAll();
	






}


