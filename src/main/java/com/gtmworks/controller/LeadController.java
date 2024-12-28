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

import com.gtmworks.domain.Lead;
import com.gtmworks.dto.LeadDTO;
import com.gtmworks.dto.LeadSearchDTO;
import com.gtmworks.dto.LeadPageDTO;
import com.gtmworks.service.LeadService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/lead")
@RestController
public class LeadController {

	private final static Logger logger = LoggerFactory.getLogger(LeadController.class);

	@Autowired
	LeadService leadService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Lead> getAll() {

		List<Lead> leads = leadService.findAll();
		
		return leads;	
	}

	@GetMapping(value = "/{leadId}")
	@ResponseBody
	public LeadDTO getLead(@PathVariable Integer leadId) {
		
		return (leadService.getLeadDTOById(leadId));
	}

 	@RequestMapping(value = "/addLead", method = RequestMethod.POST)
	public ResponseEntity<?> addLead(@RequestBody LeadDTO leadDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = leadService.addLead(leadDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/leads")
	public ResponseEntity<LeadPageDTO> getLeads(LeadSearchDTO leadSearchDTO) {
 
		return leadService.getLeads(leadSearchDTO);
	}	

	@RequestMapping(value = "/updateLead", method = RequestMethod.POST)
	public ResponseEntity<?> updateLead(@RequestBody LeadDTO leadDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = leadService.updateLead(leadDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
