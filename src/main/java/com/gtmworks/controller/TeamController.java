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

import com.gtmworks.domain.Team;
import com.gtmworks.dto.TeamDTO;
import com.gtmworks.dto.TeamSearchDTO;
import com.gtmworks.dto.TeamPageDTO;
import com.gtmworks.service.TeamService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/team")
@RestController
public class TeamController {

	private final static Logger logger = LoggerFactory.getLogger(TeamController.class);

	@Autowired
	TeamService teamService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Team> getAll() {

		List<Team> teams = teamService.findAll();
		
		return teams;	
	}

	@GetMapping(value = "/{teamId}")
	@ResponseBody
	public TeamDTO getTeam(@PathVariable Integer teamId) {
		
		return (teamService.getTeamDTOById(teamId));
	}

 	@RequestMapping(value = "/addTeam", method = RequestMethod.POST)
	public ResponseEntity<?> addTeam(@RequestBody TeamDTO teamDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = teamService.addTeam(teamDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/teams")
	public ResponseEntity<TeamPageDTO> getTeams(TeamSearchDTO teamSearchDTO) {
 
		return teamService.getTeams(teamSearchDTO);
	}	

	@RequestMapping(value = "/updateTeam", method = RequestMethod.POST)
	public ResponseEntity<?> updateTeam(@RequestBody TeamDTO teamDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = teamService.updateTeam(teamDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
