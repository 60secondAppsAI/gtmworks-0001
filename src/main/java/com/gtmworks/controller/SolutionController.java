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

import com.gtmworks.domain.Solution;
import com.gtmworks.dto.SolutionDTO;
import com.gtmworks.dto.SolutionSearchDTO;
import com.gtmworks.dto.SolutionPageDTO;
import com.gtmworks.service.SolutionService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/solution")
@RestController
public class SolutionController {

	private final static Logger logger = LoggerFactory.getLogger(SolutionController.class);

	@Autowired
	SolutionService solutionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Solution> getAll() {

		List<Solution> solutions = solutionService.findAll();
		
		return solutions;	
	}

	@GetMapping(value = "/{solutionId}")
	@ResponseBody
	public SolutionDTO getSolution(@PathVariable Integer solutionId) {
		
		return (solutionService.getSolutionDTOById(solutionId));
	}

 	@RequestMapping(value = "/addSolution", method = RequestMethod.POST)
	public ResponseEntity<?> addSolution(@RequestBody SolutionDTO solutionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = solutionService.addSolution(solutionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/solutions")
	public ResponseEntity<SolutionPageDTO> getSolutions(SolutionSearchDTO solutionSearchDTO) {
 
		return solutionService.getSolutions(solutionSearchDTO);
	}	

	@RequestMapping(value = "/updateSolution", method = RequestMethod.POST)
	public ResponseEntity<?> updateSolution(@RequestBody SolutionDTO solutionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = solutionService.updateSolution(solutionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
