package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Campaign;
import com.gtmworks.dto.CampaignDTO;
import com.gtmworks.dto.CampaignSearchDTO;
import com.gtmworks.dto.CampaignPageDTO;
import com.gtmworks.dto.CampaignConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CampaignService extends GenericService<Campaign, Integer> {

	List<Campaign> findAll();

	ResultDTO addCampaign(CampaignDTO campaignDTO, RequestDTO requestDTO);

	ResultDTO updateCampaign(CampaignDTO campaignDTO, RequestDTO requestDTO);

    Page<Campaign> getAllCampaigns(Pageable pageable);

    Page<Campaign> getAllCampaigns(Specification<Campaign> spec, Pageable pageable);

	ResponseEntity<CampaignPageDTO> getCampaigns(CampaignSearchDTO campaignSearchDTO);
	
	List<CampaignDTO> convertCampaignsToCampaignDTOs(List<Campaign> campaigns, CampaignConvertCriteriaDTO convertCriteria);

	CampaignDTO getCampaignDTOById(Integer campaignId);







}





