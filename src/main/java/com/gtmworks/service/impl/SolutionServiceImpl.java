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
import com.gtmworks.dao.SolutionDAO;
import com.gtmworks.domain.Solution;
import com.gtmworks.dto.SolutionDTO;
import com.gtmworks.dto.SolutionSearchDTO;
import com.gtmworks.dto.SolutionPageDTO;
import com.gtmworks.dto.SolutionConvertCriteriaDTO;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import com.gtmworks.service.SolutionService;
import com.gtmworks.util.ControllerUtils;





@Service
public class SolutionServiceImpl extends GenericServiceImpl<Solution, Integer> implements SolutionService {

    private final static Logger logger = LoggerFactory.getLogger(SolutionServiceImpl.class);

	@Autowired
	SolutionDAO solutionDao;

	


	@Override
	public GenericDAO<Solution, Integer> getDAO() {
		return (GenericDAO<Solution, Integer>) solutionDao;
	}
	
	public List<Solution> findAll () {
		List<Solution> solutions = solutionDao.findAll();
		
		return solutions;	
		
	}

	public ResultDTO addSolution(SolutionDTO solutionDTO, RequestDTO requestDTO) {

		Solution solution = new Solution();

		solution.setSolutionId(solutionDTO.getSolutionId());


		solution.setSolutionTitle(solutionDTO.getSolutionTitle());


		solution.setDescription(solutionDTO.getDescription());


		solution.setVersion(solutionDTO.getVersion());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		solution = solutionDao.save(solution);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Solution> getAllSolutions(Pageable pageable) {
		return solutionDao.findAll(pageable);
	}

	public Page<Solution> getAllSolutions(Specification<Solution> spec, Pageable pageable) {
		return solutionDao.findAll(spec, pageable);
	}

	public ResponseEntity<SolutionPageDTO> getSolutions(SolutionSearchDTO solutionSearchDTO) {
	
			Integer solutionId = solutionSearchDTO.getSolutionId(); 
 			String solutionTitle = solutionSearchDTO.getSolutionTitle(); 
 			String description = solutionSearchDTO.getDescription(); 
 			String version = solutionSearchDTO.getVersion(); 
 			String sortBy = solutionSearchDTO.getSortBy();
			String sortOrder = solutionSearchDTO.getSortOrder();
			String searchQuery = solutionSearchDTO.getSearchQuery();
			Integer page = solutionSearchDTO.getPage();
			Integer size = solutionSearchDTO.getSize();

	        Specification<Solution> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, solutionId, "solutionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, solutionTitle, "solutionTitle"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			spec = ControllerUtils.andIfNecessary(spec, version, "version"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("solutionTitle")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("version")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Solution> solutions = this.getAllSolutions(spec, pageable);
		
		//System.out.println(String.valueOf(solutions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(solutions.getTotalPages()));
		
		List<Solution> solutionsList = solutions.getContent();
		
		SolutionConvertCriteriaDTO convertCriteria = new SolutionConvertCriteriaDTO();
		List<SolutionDTO> solutionDTOs = this.convertSolutionsToSolutionDTOs(solutionsList,convertCriteria);
		
		SolutionPageDTO solutionPageDTO = new SolutionPageDTO();
		solutionPageDTO.setSolutions(solutionDTOs);
		solutionPageDTO.setTotalElements(solutions.getTotalElements());
		return ResponseEntity.ok(solutionPageDTO);
	}

	public List<SolutionDTO> convertSolutionsToSolutionDTOs(List<Solution> solutions, SolutionConvertCriteriaDTO convertCriteria) {
		
		List<SolutionDTO> solutionDTOs = new ArrayList<SolutionDTO>();
		
		for (Solution solution : solutions) {
			solutionDTOs.add(convertSolutionToSolutionDTO(solution,convertCriteria));
		}
		
		return solutionDTOs;

	}
	
	public SolutionDTO convertSolutionToSolutionDTO(Solution solution, SolutionConvertCriteriaDTO convertCriteria) {
		
		SolutionDTO solutionDTO = new SolutionDTO();
		
		solutionDTO.setSolutionId(solution.getSolutionId());

	
		solutionDTO.setSolutionTitle(solution.getSolutionTitle());

	
		solutionDTO.setDescription(solution.getDescription());

	
		solutionDTO.setVersion(solution.getVersion());

	

		
		return solutionDTO;
	}

	public ResultDTO updateSolution(SolutionDTO solutionDTO, RequestDTO requestDTO) {
		
		Solution solution = solutionDao.getById(solutionDTO.getSolutionId());

		solution.setSolutionId(ControllerUtils.setValue(solution.getSolutionId(), solutionDTO.getSolutionId()));

		solution.setSolutionTitle(ControllerUtils.setValue(solution.getSolutionTitle(), solutionDTO.getSolutionTitle()));

		solution.setDescription(ControllerUtils.setValue(solution.getDescription(), solutionDTO.getDescription()));

		solution.setVersion(ControllerUtils.setValue(solution.getVersion(), solutionDTO.getVersion()));



        solution = solutionDao.save(solution);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SolutionDTO getSolutionDTOById(Integer solutionId) {
	
		Solution solution = solutionDao.getById(solutionId);
			
		
		SolutionConvertCriteriaDTO convertCriteria = new SolutionConvertCriteriaDTO();
		return(this.convertSolutionToSolutionDTO(solution,convertCriteria));
	}







}
