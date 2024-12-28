package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Case;
import com.gtmworks.dto.CaseDTO;
import com.gtmworks.dto.CaseSearchDTO;
import com.gtmworks.dto.CasePageDTO;
import com.gtmworks.dto.CaseConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CaseService extends GenericService<Case, Integer> {

	List<Case> findAll();

	ResultDTO addCase(CaseDTO caseDTO, RequestDTO requestDTO);

	ResultDTO updateCase(CaseDTO caseDTO, RequestDTO requestDTO);

    Page<Case> getAllCases(Pageable pageable);

    Page<Case> getAllCases(Specification<Case> spec, Pageable pageable);

	ResponseEntity<CasePageDTO> getCases(CaseSearchDTO caseSearchDTO);
	
	List<CaseDTO> convertCasesToCaseDTOs(List<Case> cases, CaseConvertCriteriaDTO convertCriteria);

	CaseDTO getCaseDTOById(Integer caseId);







}





