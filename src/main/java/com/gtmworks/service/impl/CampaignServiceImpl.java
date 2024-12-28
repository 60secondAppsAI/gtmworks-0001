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
import com.gtmworks.dao.CampaignDAO;
import com.gtmworks.domain.Campaign;
import com.gtmworks.dto.CampaignDTO;
import com.gtmworks.dto.CampaignSearchDTO;
import com.gtmworks.dto.CampaignPageDTO;
import com.gtmworks.dto.CampaignConvertCriteriaDTO;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import com.gtmworks.service.CampaignService;
import com.gtmworks.util.ControllerUtils;





@Service
public class CampaignServiceImpl extends GenericServiceImpl<Campaign, Integer> implements CampaignService {

    private final static Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);

	@Autowired
	CampaignDAO campaignDao;

	


	@Override
	public GenericDAO<Campaign, Integer> getDAO() {
		return (GenericDAO<Campaign, Integer>) campaignDao;
	}
	
	public List<Campaign> findAll () {
		List<Campaign> campaigns = campaignDao.findAll();
		
		return campaigns;	
		
	}

	public ResultDTO addCampaign(CampaignDTO campaignDTO, RequestDTO requestDTO) {

		Campaign campaign = new Campaign();

		campaign.setCampaignId(campaignDTO.getCampaignId());


		campaign.setCampaignName(campaignDTO.getCampaignName());


		campaign.setStartDate(campaignDTO.getStartDate());


		campaign.setEndDate(campaignDTO.getEndDate());


		campaign.setBudget(campaignDTO.getBudget());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		campaign = campaignDao.save(campaign);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Campaign> getAllCampaigns(Pageable pageable) {
		return campaignDao.findAll(pageable);
	}

	public Page<Campaign> getAllCampaigns(Specification<Campaign> spec, Pageable pageable) {
		return campaignDao.findAll(spec, pageable);
	}

	public ResponseEntity<CampaignPageDTO> getCampaigns(CampaignSearchDTO campaignSearchDTO) {
	
			Integer campaignId = campaignSearchDTO.getCampaignId(); 
 			String campaignName = campaignSearchDTO.getCampaignName(); 
      			String sortBy = campaignSearchDTO.getSortBy();
			String sortOrder = campaignSearchDTO.getSortOrder();
			String searchQuery = campaignSearchDTO.getSearchQuery();
			Integer page = campaignSearchDTO.getPage();
			Integer size = campaignSearchDTO.getSize();

	        Specification<Campaign> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, campaignId, "campaignId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, campaignName, "campaignName"); 
			
 			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("campaignName")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Campaign> campaigns = this.getAllCampaigns(spec, pageable);
		
		//System.out.println(String.valueOf(campaigns.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(campaigns.getTotalPages()));
		
		List<Campaign> campaignsList = campaigns.getContent();
		
		CampaignConvertCriteriaDTO convertCriteria = new CampaignConvertCriteriaDTO();
		List<CampaignDTO> campaignDTOs = this.convertCampaignsToCampaignDTOs(campaignsList,convertCriteria);
		
		CampaignPageDTO campaignPageDTO = new CampaignPageDTO();
		campaignPageDTO.setCampaigns(campaignDTOs);
		campaignPageDTO.setTotalElements(campaigns.getTotalElements());
		return ResponseEntity.ok(campaignPageDTO);
	}

	public List<CampaignDTO> convertCampaignsToCampaignDTOs(List<Campaign> campaigns, CampaignConvertCriteriaDTO convertCriteria) {
		
		List<CampaignDTO> campaignDTOs = new ArrayList<CampaignDTO>();
		
		for (Campaign campaign : campaigns) {
			campaignDTOs.add(convertCampaignToCampaignDTO(campaign,convertCriteria));
		}
		
		return campaignDTOs;

	}
	
	public CampaignDTO convertCampaignToCampaignDTO(Campaign campaign, CampaignConvertCriteriaDTO convertCriteria) {
		
		CampaignDTO campaignDTO = new CampaignDTO();
		
		campaignDTO.setCampaignId(campaign.getCampaignId());

	
		campaignDTO.setCampaignName(campaign.getCampaignName());

	
		campaignDTO.setStartDate(campaign.getStartDate());

	
		campaignDTO.setEndDate(campaign.getEndDate());

	
		campaignDTO.setBudget(campaign.getBudget());

	

		
		return campaignDTO;
	}

	public ResultDTO updateCampaign(CampaignDTO campaignDTO, RequestDTO requestDTO) {
		
		Campaign campaign = campaignDao.getById(campaignDTO.getCampaignId());

		campaign.setCampaignId(ControllerUtils.setValue(campaign.getCampaignId(), campaignDTO.getCampaignId()));

		campaign.setCampaignName(ControllerUtils.setValue(campaign.getCampaignName(), campaignDTO.getCampaignName()));

		campaign.setStartDate(ControllerUtils.setValue(campaign.getStartDate(), campaignDTO.getStartDate()));

		campaign.setEndDate(ControllerUtils.setValue(campaign.getEndDate(), campaignDTO.getEndDate()));

		campaign.setBudget(ControllerUtils.setValue(campaign.getBudget(), campaignDTO.getBudget()));



        campaign = campaignDao.save(campaign);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CampaignDTO getCampaignDTOById(Integer campaignId) {
	
		Campaign campaign = campaignDao.getById(campaignId);
			
		
		CampaignConvertCriteriaDTO convertCriteria = new CampaignConvertCriteriaDTO();
		return(this.convertCampaignToCampaignDTO(campaign,convertCriteria));
	}







}
