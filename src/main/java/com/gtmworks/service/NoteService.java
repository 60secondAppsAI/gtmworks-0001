package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Note;
import com.gtmworks.dto.NoteDTO;
import com.gtmworks.dto.NoteSearchDTO;
import com.gtmworks.dto.NotePageDTO;
import com.gtmworks.dto.NoteConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface NoteService extends GenericService<Note, Integer> {

	List<Note> findAll();

	ResultDTO addNote(NoteDTO noteDTO, RequestDTO requestDTO);

	ResultDTO updateNote(NoteDTO noteDTO, RequestDTO requestDTO);

    Page<Note> getAllNotes(Pageable pageable);

    Page<Note> getAllNotes(Specification<Note> spec, Pageable pageable);

	ResponseEntity<NotePageDTO> getNotes(NoteSearchDTO noteSearchDTO);
	
	List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes, NoteConvertCriteriaDTO convertCriteria);

	NoteDTO getNoteDTOById(Integer noteId);







}





