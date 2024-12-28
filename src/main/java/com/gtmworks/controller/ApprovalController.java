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

import com.gtmworks.domain.Approval;
import com.gtmworks.dto.ApprovalDTO;
import com.gtmworks.dto.ApprovalSearchDTO;
import com.gtmworks.dto.ApprovalPageDTO;
import com.gtmworks.service.ApprovalService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/approval")
@RestController
public class ApprovalController {

	private final static Logger logger = LoggerFactory.getLogger(ApprovalController.class);

	@Autowired
	ApprovalService approvalService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Approval> getAll() {

		List<Approval> approvals = approvalService.findAll();
		
		return approvals;	
	}

	@GetMapping(value = "/{approvalId}")
	@ResponseBody
	public ApprovalDTO getApproval(@PathVariable Integer approvalId) {
		
		return (approvalService.getApprovalDTOById(approvalId));
	}

 	@RequestMapping(value = "/addApproval", method = RequestMethod.POST)
	public ResponseEntity<?> addApproval(@RequestBody ApprovalDTO approvalDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = approvalService.addApproval(approvalDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/approvals")
	public ResponseEntity<ApprovalPageDTO> getApprovals(ApprovalSearchDTO approvalSearchDTO) {
 
		return approvalService.getApprovals(approvalSearchDTO);
	}	

	@RequestMapping(value = "/updateApproval", method = RequestMethod.POST)
	public ResponseEntity<?> updateApproval(@RequestBody ApprovalDTO approvalDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = approvalService.updateApproval(approvalDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
