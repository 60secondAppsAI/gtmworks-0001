package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Team;
import com.gtmworks.dto.TeamDTO;
import com.gtmworks.dto.TeamSearchDTO;
import com.gtmworks.dto.TeamPageDTO;
import com.gtmworks.dto.TeamConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TeamService extends GenericService<Team, Integer> {

	List<Team> findAll();

	ResultDTO addTeam(TeamDTO teamDTO, RequestDTO requestDTO);

	ResultDTO updateTeam(TeamDTO teamDTO, RequestDTO requestDTO);

    Page<Team> getAllTeams(Pageable pageable);

    Page<Team> getAllTeams(Specification<Team> spec, Pageable pageable);

	ResponseEntity<TeamPageDTO> getTeams(TeamSearchDTO teamSearchDTO);
	
	List<TeamDTO> convertTeamsToTeamDTOs(List<Team> teams, TeamConvertCriteriaDTO convertCriteria);

	TeamDTO getTeamDTOById(Integer teamId);







}





