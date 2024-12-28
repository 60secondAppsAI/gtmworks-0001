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
import com.gtmworks.dao.LeadDAO;
import com.gtmworks.domain.Lead;
import com.gtmworks.dto.LeadDTO;
import com.gtmworks.dto.LeadSearchDTO;
import com.gtmworks.dto.LeadPageDTO;
import com.gtmworks.dto.LeadConvertCriteriaDTO;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import com.gtmworks.service.LeadService;
import com.gtmworks.util.ControllerUtils;





@Service
public class LeadServiceImpl extends GenericServiceImpl<Lead, Integer> implements LeadService {

    private final static Logger logger = LoggerFactory.getLogger(LeadServiceImpl.class);

	@Autowired
	LeadDAO leadDao;

	


	@Override
	public GenericDAO<Lead, Integer> getDAO() {
		return (GenericDAO<Lead, Integer>) leadDao;
	}
	
	public List<Lead> findAll () {
		List<Lead> leads = leadDao.findAll();
		
		return leads;	
		
	}

	public ResultDTO addLead(LeadDTO leadDTO, RequestDTO requestDTO) {

		Lead lead = new Lead();

		lead.setLeadId(leadDTO.getLeadId());


		lead.setFirstName(leadDTO.getFirstName());


		lead.setLastName(leadDTO.getLastName());


		lead.setEmail(leadDTO.getEmail());


		lead.setPhoneNumber(leadDTO.getPhoneNumber());


		lead.setLeadSource(leadDTO.getLeadSource());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		lead = leadDao.save(lead);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Lead> getAllLeads(Pageable pageable) {
		return leadDao.findAll(pageable);
	}

	public Page<Lead> getAllLeads(Specification<Lead> spec, Pageable pageable) {
		return leadDao.findAll(spec, pageable);
	}

	public ResponseEntity<LeadPageDTO> getLeads(LeadSearchDTO leadSearchDTO) {
	
			Integer leadId = leadSearchDTO.getLeadId(); 
 			String firstName = leadSearchDTO.getFirstName(); 
 			String lastName = leadSearchDTO.getLastName(); 
 			String email = leadSearchDTO.getEmail(); 
 			String phoneNumber = leadSearchDTO.getPhoneNumber(); 
 			String leadSource = leadSearchDTO.getLeadSource(); 
 			String sortBy = leadSearchDTO.getSortBy();
			String sortOrder = leadSearchDTO.getSortOrder();
			String searchQuery = leadSearchDTO.getSearchQuery();
			Integer page = leadSearchDTO.getPage();
			Integer size = leadSearchDTO.getSize();

	        Specification<Lead> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, leadId, "leadId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, firstName, "firstName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, lastName, "lastName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, email, "email"); 
			
			spec = ControllerUtils.andIfNecessary(spec, phoneNumber, "phoneNumber"); 
			
			spec = ControllerUtils.andIfNecessary(spec, leadSource, "leadSource"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("firstName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("lastName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("email")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("phoneNumber")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("leadSource")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Lead> leads = this.getAllLeads(spec, pageable);
		
		//System.out.println(String.valueOf(leads.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(leads.getTotalPages()));
		
		List<Lead> leadsList = leads.getContent();
		
		LeadConvertCriteriaDTO convertCriteria = new LeadConvertCriteriaDTO();
		List<LeadDTO> leadDTOs = this.convertLeadsToLeadDTOs(leadsList,convertCriteria);
		
		LeadPageDTO leadPageDTO = new LeadPageDTO();
		leadPageDTO.setLeads(leadDTOs);
		leadPageDTO.setTotalElements(leads.getTotalElements());
		return ResponseEntity.ok(leadPageDTO);
	}

	public List<LeadDTO> convertLeadsToLeadDTOs(List<Lead> leads, LeadConvertCriteriaDTO convertCriteria) {
		
		List<LeadDTO> leadDTOs = new ArrayList<LeadDTO>();
		
		for (Lead lead : leads) {
			leadDTOs.add(convertLeadToLeadDTO(lead,convertCriteria));
		}
		
		return leadDTOs;

	}
	
	public LeadDTO convertLeadToLeadDTO(Lead lead, LeadConvertCriteriaDTO convertCriteria) {
		
		LeadDTO leadDTO = new LeadDTO();
		
		leadDTO.setLeadId(lead.getLeadId());

	
		leadDTO.setFirstName(lead.getFirstName());

	
		leadDTO.setLastName(lead.getLastName());

	
		leadDTO.setEmail(lead.getEmail());

	
		leadDTO.setPhoneNumber(lead.getPhoneNumber());

	
		leadDTO.setLeadSource(lead.getLeadSource());

	

		
		return leadDTO;
	}

	public ResultDTO updateLead(LeadDTO leadDTO, RequestDTO requestDTO) {
		
		Lead lead = leadDao.getById(leadDTO.getLeadId());

		lead.setLeadId(ControllerUtils.setValue(lead.getLeadId(), leadDTO.getLeadId()));

		lead.setFirstName(ControllerUtils.setValue(lead.getFirstName(), leadDTO.getFirstName()));

		lead.setLastName(ControllerUtils.setValue(lead.getLastName(), leadDTO.getLastName()));

		lead.setEmail(ControllerUtils.setValue(lead.getEmail(), leadDTO.getEmail()));

		lead.setPhoneNumber(ControllerUtils.setValue(lead.getPhoneNumber(), leadDTO.getPhoneNumber()));

		lead.setLeadSource(ControllerUtils.setValue(lead.getLeadSource(), leadDTO.getLeadSource()));



        lead = leadDao.save(lead);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public LeadDTO getLeadDTOById(Integer leadId) {
	
		Lead lead = leadDao.getById(leadId);
			
		
		LeadConvertCriteriaDTO convertCriteria = new LeadConvertCriteriaDTO();
		return(this.convertLeadToLeadDTO(lead,convertCriteria));
	}







}
