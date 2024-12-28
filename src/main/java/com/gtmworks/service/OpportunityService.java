package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Opportunity;
import com.gtmworks.dto.OpportunityDTO;
import com.gtmworks.dto.OpportunitySearchDTO;
import com.gtmworks.dto.OpportunityPageDTO;
import com.gtmworks.dto.OpportunityConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface OpportunityService extends GenericService<Opportunity, Integer> {

	List<Opportunity> findAll();

	ResultDTO addOpportunity(OpportunityDTO opportunityDTO, RequestDTO requestDTO);

	ResultDTO updateOpportunity(OpportunityDTO opportunityDTO, RequestDTO requestDTO);

    Page<Opportunity> getAllOpportunitys(Pageable pageable);

    Page<Opportunity> getAllOpportunitys(Specification<Opportunity> spec, Pageable pageable);

	ResponseEntity<OpportunityPageDTO> getOpportunitys(OpportunitySearchDTO opportunitySearchDTO);
	
	List<OpportunityDTO> convertOpportunitysToOpportunityDTOs(List<Opportunity> opportunitys, OpportunityConvertCriteriaDTO convertCriteria);

	OpportunityDTO getOpportunityDTOById(Integer opportunityId);







}





