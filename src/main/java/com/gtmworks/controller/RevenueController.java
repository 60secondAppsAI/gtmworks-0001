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

import com.gtmworks.domain.Revenue;
import com.gtmworks.dto.RevenueDTO;
import com.gtmworks.dto.RevenueSearchDTO;
import com.gtmworks.dto.RevenuePageDTO;
import com.gtmworks.service.RevenueService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/revenue")
@RestController
public class RevenueController {

	private final static Logger logger = LoggerFactory.getLogger(RevenueController.class);

	@Autowired
	RevenueService revenueService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Revenue> getAll() {

		List<Revenue> revenues = revenueService.findAll();
		
		return revenues;	
	}

	@GetMapping(value = "/{revenueId}")
	@ResponseBody
	public RevenueDTO getRevenue(@PathVariable Integer revenueId) {
		
		return (revenueService.getRevenueDTOById(revenueId));
	}

 	@RequestMapping(value = "/addRevenue", method = RequestMethod.POST)
	public ResponseEntity<?> addRevenue(@RequestBody RevenueDTO revenueDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = revenueService.addRevenue(revenueDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/revenues")
	public ResponseEntity<RevenuePageDTO> getRevenues(RevenueSearchDTO revenueSearchDTO) {
 
		return revenueService.getRevenues(revenueSearchDTO);
	}	

	@RequestMapping(value = "/updateRevenue", method = RequestMethod.POST)
	public ResponseEntity<?> updateRevenue(@RequestBody RevenueDTO revenueDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = revenueService.updateRevenue(revenueDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
