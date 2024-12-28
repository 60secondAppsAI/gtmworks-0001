package com.gtmworks.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.gtmworks.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.gtmworks.domain.Note;
import com.gtmworks.dto.NoteDTO;
import com.gtmworks.dto.NoteSearchDTO;
import com.gtmworks.dto.NotePageDTO;
import com.gtmworks.service.NoteService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/note")
@RestController
public class NoteController {

	private final static Logger logger = LoggerFactory.getLogger(NoteController.class);

	@Autowired
	NoteService noteService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Note> getAll() {

		List<Note> notes = noteService.findAll();
		
		return notes;	
	}

	@GetMapping(value = "/{noteId}")
	@ResponseBody
	public NoteDTO getNote(@PathVariable Integer noteId) {
		
		return (noteService.getNoteDTOById(noteId));
	}

 	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public ResponseEntity<?> addNote(@RequestBody NoteDTO noteDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = noteService.addNote(noteDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/notes")
	public ResponseEntity<NotePageDTO> getNotes(NoteSearchDTO noteSearchDTO) {
 
		return noteService.getNotes(noteSearchDTO);
	}	

	@RequestMapping(value = "/updateNote", method = RequestMethod.POST)
	public ResponseEntity<?> updateNote(@RequestBody NoteDTO noteDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = noteService.updateNote(noteDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
