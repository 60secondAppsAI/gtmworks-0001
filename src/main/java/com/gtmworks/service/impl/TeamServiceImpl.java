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
import com.gtmworks.dao.TeamDAO;
import com.gtmworks.domain.Team;
import com.gtmworks.dto.TeamDTO;
import com.gtmworks.dto.TeamSearchDTO;
import com.gtmworks.dto.TeamPageDTO;
import com.gtmworks.dto.TeamConvertCriteriaDTO;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import com.gtmworks.service.TeamService;
import com.gtmworks.util.ControllerUtils;





@Service
public class TeamServiceImpl extends GenericServiceImpl<Team, Integer> implements TeamService {

    private final static Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

	@Autowired
	TeamDAO teamDao;

	


	@Override
	public GenericDAO<Team, Integer> getDAO() {
		return (GenericDAO<Team, Integer>) teamDao;
	}
	
	public List<Team> findAll () {
		List<Team> teams = teamDao.findAll();
		
		return teams;	
		
	}

	public ResultDTO addTeam(TeamDTO teamDTO, RequestDTO requestDTO) {

		Team team = new Team();

		team.setTeamId(teamDTO.getTeamId());


		team.setTeamName(teamDTO.getTeamName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		team = teamDao.save(team);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Team> getAllTeams(Pageable pageable) {
		return teamDao.findAll(pageable);
	}

	public Page<Team> getAllTeams(Specification<Team> spec, Pageable pageable) {
		return teamDao.findAll(spec, pageable);
	}

	public ResponseEntity<TeamPageDTO> getTeams(TeamSearchDTO teamSearchDTO) {
	
			Integer teamId = teamSearchDTO.getTeamId(); 
 			String teamName = teamSearchDTO.getTeamName(); 
 			String sortBy = teamSearchDTO.getSortBy();
			String sortOrder = teamSearchDTO.getSortOrder();
			String searchQuery = teamSearchDTO.getSearchQuery();
			Integer page = teamSearchDTO.getPage();
			Integer size = teamSearchDTO.getSize();

	        Specification<Team> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, teamId, "teamId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, teamName, "teamName"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("teamName")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Team> teams = this.getAllTeams(spec, pageable);
		
		//System.out.println(String.valueOf(teams.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(teams.getTotalPages()));
		
		List<Team> teamsList = teams.getContent();
		
		TeamConvertCriteriaDTO convertCriteria = new TeamConvertCriteriaDTO();
		List<TeamDTO> teamDTOs = this.convertTeamsToTeamDTOs(teamsList,convertCriteria);
		
		TeamPageDTO teamPageDTO = new TeamPageDTO();
		teamPageDTO.setTeams(teamDTOs);
		teamPageDTO.setTotalElements(teams.getTotalElements());
		return ResponseEntity.ok(teamPageDTO);
	}

	public List<TeamDTO> convertTeamsToTeamDTOs(List<Team> teams, TeamConvertCriteriaDTO convertCriteria) {
		
		List<TeamDTO> teamDTOs = new ArrayList<TeamDTO>();
		
		for (Team team : teams) {
			teamDTOs.add(convertTeamToTeamDTO(team,convertCriteria));
		}
		
		return teamDTOs;

	}
	
	public TeamDTO convertTeamToTeamDTO(Team team, TeamConvertCriteriaDTO convertCriteria) {
		
		TeamDTO teamDTO = new TeamDTO();
		
		teamDTO.setTeamId(team.getTeamId());

	
		teamDTO.setTeamName(team.getTeamName());

	

		
		return teamDTO;
	}

	public ResultDTO updateTeam(TeamDTO teamDTO, RequestDTO requestDTO) {
		
		Team team = teamDao.getById(teamDTO.getTeamId());

		team.setTeamId(ControllerUtils.setValue(team.getTeamId(), teamDTO.getTeamId()));

		team.setTeamName(ControllerUtils.setValue(team.getTeamName(), teamDTO.getTeamName()));



        team = teamDao.save(team);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TeamDTO getTeamDTOById(Integer teamId) {
	
		Team team = teamDao.getById(teamId);
			
		
		TeamConvertCriteriaDTO convertCriteria = new TeamConvertCriteriaDTO();
		return(this.convertTeamToTeamDTO(team,convertCriteria));
	}







}
