package com.gtmworks.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.gtmworks.dao.GenericDAO;
import com.gtmworks.service.GenericService;
import com.gtmworks.service.impl.GenericServiceImpl;
import com.gtmworks.dao.NoteDAO;
import com.gtmworks.domain.Note;
import com.gtmworks.dto.NoteDTO;
import com.gtmworks.dto.NoteSearchDTO;
import com.gtmworks.dto.NotePageDTO;
import com.gtmworks.dto.NoteConvertCriteriaDTO;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import com.gtmworks.service.NoteService;
import com.gtmworks.util.ControllerUtils;





@Service
public class NoteServiceImpl extends GenericServiceImpl<Note, Integer> implements NoteService {

    private final static Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

	@Autowired
	NoteDAO noteDao;

	


	@Override
	public GenericDAO<Note, Integer> getDAO() {
		return (GenericDAO<Note, Integer>) noteDao;
	}
	
	public List<Note> findAll () {
		List<Note> notes = noteDao.findAll();
		
		return notes;	
		
	}

	public ResultDTO addNote(NoteDTO noteDTO, RequestDTO requestDTO) {

		Note note = new Note();

		note.setNoteId(noteDTO.getNoteId());


		note.setTitle(noteDTO.getTitle());


		note.setContent(noteDTO.getContent());


		note.setCreatedDate(noteDTO.getCreatedDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		note = noteDao.save(note);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Note> getAllNotes(Pageable pageable) {
		return noteDao.findAll(pageable);
	}

	public Page<Note> getAllNotes(Specification<Note> spec, Pageable pageable) {
		return noteDao.findAll(spec, pageable);
	}

	public ResponseEntity<NotePageDTO> getNotes(NoteSearchDTO noteSearchDTO) {
	
			Integer noteId = noteSearchDTO.getNoteId(); 
 			String title = noteSearchDTO.getTitle(); 
 			String content = noteSearchDTO.getContent(); 
   			String sortBy = noteSearchDTO.getSortBy();
			String sortOrder = noteSearchDTO.getSortOrder();
			String searchQuery = noteSearchDTO.getSearchQuery();
			Integer page = noteSearchDTO.getPage();
			Integer size = noteSearchDTO.getSize();

	        Specification<Note> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, noteId, "noteId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, title, "title"); 
			
			spec = ControllerUtils.andIfNecessary(spec, content, "content"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("title")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("content")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Note> notes = this.getAllNotes(spec, pageable);
		
		//System.out.println(String.valueOf(notes.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(notes.getTotalPages()));
		
		List<Note> notesList = notes.getContent();
		
		NoteConvertCriteriaDTO convertCriteria = new NoteConvertCriteriaDTO();
		List<NoteDTO> noteDTOs = this.convertNotesToNoteDTOs(notesList,convertCriteria);
		
		NotePageDTO notePageDTO = new NotePageDTO();
		notePageDTO.setNotes(noteDTOs);
		notePageDTO.setTotalElements(notes.getTotalElements());
		return ResponseEntity.ok(notePageDTO);
	}

	public List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes, NoteConvertCriteriaDTO convertCriteria) {
		
		List<NoteDTO> noteDTOs = new ArrayList<NoteDTO>();
		
		for (Note note : notes) {
			noteDTOs.add(convertNoteToNoteDTO(note,convertCriteria));
		}
		
		return noteDTOs;

	}
	
	public NoteDTO convertNoteToNoteDTO(Note note, NoteConvertCriteriaDTO convertCriteria) {
		
		NoteDTO noteDTO = new NoteDTO();
		
		noteDTO.setNoteId(note.getNoteId());

	
		noteDTO.setTitle(note.getTitle());

	
		noteDTO.setContent(note.getContent());

	
		noteDTO.setCreatedDate(note.getCreatedDate());

	

		
		return noteDTO;
	}

	public ResultDTO updateNote(NoteDTO noteDTO, RequestDTO requestDTO) {
		
		Note note = noteDao.getById(noteDTO.getNoteId());

		note.setNoteId(ControllerUtils.setValue(note.getNoteId(), noteDTO.getNoteId()));

		note.setTitle(ControllerUtils.setValue(note.getTitle(), noteDTO.getTitle()));

		note.setContent(ControllerUtils.setValue(note.getContent(), noteDTO.getContent()));

		note.setCreatedDate(ControllerUtils.setValue(note.getCreatedDate(), noteDTO.getCreatedDate()));



        note = noteDao.save(note);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public NoteDTO getNoteDTOById(Integer noteId) {
	
		Note note = noteDao.getById(noteId);
			
		
		NoteConvertCriteriaDTO convertCriteria = new NoteConvertCriteriaDTO();
		return(this.convertNoteToNoteDTO(note,convertCriteria));
	}







}
