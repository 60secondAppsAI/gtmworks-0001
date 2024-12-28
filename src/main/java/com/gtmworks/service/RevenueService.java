package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Revenue;
import com.gtmworks.dto.RevenueDTO;
import com.gtmworks.dto.RevenueSearchDTO;
import com.gtmworks.dto.RevenuePageDTO;
import com.gtmworks.dto.RevenueConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RevenueService extends GenericService<Revenue, Integer> {

	List<Revenue> findAll();

	ResultDTO addRevenue(RevenueDTO revenueDTO, RequestDTO requestDTO);

	ResultDTO updateRevenue(RevenueDTO revenueDTO, RequestDTO requestDTO);

    Page<Revenue> getAllRevenues(Pageable pageable);

    Page<Revenue> getAllRevenues(Specification<Revenue> spec, Pageable pageable);

	ResponseEntity<RevenuePageDTO> getRevenues(RevenueSearchDTO revenueSearchDTO);
	
	List<RevenueDTO> convertRevenuesToRevenueDTOs(List<Revenue> revenues, RevenueConvertCriteriaDTO convertCriteria);

	RevenueDTO getRevenueDTOById(Integer revenueId);







}





