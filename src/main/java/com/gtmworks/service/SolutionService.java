package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Solution;
import com.gtmworks.dto.SolutionDTO;
import com.gtmworks.dto.SolutionSearchDTO;
import com.gtmworks.dto.SolutionPageDTO;
import com.gtmworks.dto.SolutionConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SolutionService extends GenericService<Solution, Integer> {

	List<Solution> findAll();

	ResultDTO addSolution(SolutionDTO solutionDTO, RequestDTO requestDTO);

	ResultDTO updateSolution(SolutionDTO solutionDTO, RequestDTO requestDTO);

    Page<Solution> getAllSolutions(Pageable pageable);

    Page<Solution> getAllSolutions(Specification<Solution> spec, Pageable pageable);

	ResponseEntity<SolutionPageDTO> getSolutions(SolutionSearchDTO solutionSearchDTO);
	
	List<SolutionDTO> convertSolutionsToSolutionDTOs(List<Solution> solutions, SolutionConvertCriteriaDTO convertCriteria);

	SolutionDTO getSolutionDTOById(Integer solutionId);







}





