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
import com.gtmworks.dao.RevenueDAO;
import com.gtmworks.domain.Revenue;
import com.gtmworks.dto.RevenueDTO;
import com.gtmworks.dto.RevenueSearchDTO;
import com.gtmworks.dto.RevenuePageDTO;
import com.gtmworks.dto.RevenueConvertCriteriaDTO;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import com.gtmworks.service.RevenueService;
import com.gtmworks.util.ControllerUtils;





@Service
public class RevenueServiceImpl extends GenericServiceImpl<Revenue, Integer> implements RevenueService {

    private final static Logger logger = LoggerFactory.getLogger(RevenueServiceImpl.class);

	@Autowired
	RevenueDAO revenueDao;

	


	@Override
	public GenericDAO<Revenue, Integer> getDAO() {
		return (GenericDAO<Revenue, Integer>) revenueDao;
	}
	
	public List<Revenue> findAll () {
		List<Revenue> revenues = revenueDao.findAll();
		
		return revenues;	
		
	}

	public ResultDTO addRevenue(RevenueDTO revenueDTO, RequestDTO requestDTO) {

		Revenue revenue = new Revenue();

		revenue.setRevenueId(revenueDTO.getRevenueId());


		revenue.setYear(revenueDTO.getYear());


		revenue.setQuarter(revenueDTO.getQuarter());


		revenue.setRevenueAmount(revenueDTO.getRevenueAmount());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		revenue = revenueDao.save(revenue);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Revenue> getAllRevenues(Pageable pageable) {
		return revenueDao.findAll(pageable);
	}

	public Page<Revenue> getAllRevenues(Specification<Revenue> spec, Pageable pageable) {
		return revenueDao.findAll(spec, pageable);
	}

	public ResponseEntity<RevenuePageDTO> getRevenues(RevenueSearchDTO revenueSearchDTO) {
	
			Integer revenueId = revenueSearchDTO.getRevenueId(); 
 			Integer year = revenueSearchDTO.getYear(); 
 			Integer quarter = revenueSearchDTO.getQuarter(); 
  			String sortBy = revenueSearchDTO.getSortBy();
			String sortOrder = revenueSearchDTO.getSortOrder();
			String searchQuery = revenueSearchDTO.getSearchQuery();
			Integer page = revenueSearchDTO.getPage();
			Integer size = revenueSearchDTO.getSize();

	        Specification<Revenue> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, revenueId, "revenueId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, year, "year"); 
			
			spec = ControllerUtils.andIfNecessary(spec, quarter, "quarter"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<Revenue> revenues = this.getAllRevenues(spec, pageable);
		
		//System.out.println(String.valueOf(revenues.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(revenues.getTotalPages()));
		
		List<Revenue> revenuesList = revenues.getContent();
		
		RevenueConvertCriteriaDTO convertCriteria = new RevenueConvertCriteriaDTO();
		List<RevenueDTO> revenueDTOs = this.convertRevenuesToRevenueDTOs(revenuesList,convertCriteria);
		
		RevenuePageDTO revenuePageDTO = new RevenuePageDTO();
		revenuePageDTO.setRevenues(revenueDTOs);
		revenuePageDTO.setTotalElements(revenues.getTotalElements());
		return ResponseEntity.ok(revenuePageDTO);
	}

	public List<RevenueDTO> convertRevenuesToRevenueDTOs(List<Revenue> revenues, RevenueConvertCriteriaDTO convertCriteria) {
		
		List<RevenueDTO> revenueDTOs = new ArrayList<RevenueDTO>();
		
		for (Revenue revenue : revenues) {
			revenueDTOs.add(convertRevenueToRevenueDTO(revenue,convertCriteria));
		}
		
		return revenueDTOs;

	}
	
	public RevenueDTO convertRevenueToRevenueDTO(Revenue revenue, RevenueConvertCriteriaDTO convertCriteria) {
		
		RevenueDTO revenueDTO = new RevenueDTO();
		
		revenueDTO.setRevenueId(revenue.getRevenueId());

	
		revenueDTO.setYear(revenue.getYear());

	
		revenueDTO.setQuarter(revenue.getQuarter());

	
		revenueDTO.setRevenueAmount(revenue.getRevenueAmount());

	

		
		return revenueDTO;
	}

	public ResultDTO updateRevenue(RevenueDTO revenueDTO, RequestDTO requestDTO) {
		
		Revenue revenue = revenueDao.getById(revenueDTO.getRevenueId());

		revenue.setRevenueId(ControllerUtils.setValue(revenue.getRevenueId(), revenueDTO.getRevenueId()));

		revenue.setYear(ControllerUtils.setValue(revenue.getYear(), revenueDTO.getYear()));

		revenue.setQuarter(ControllerUtils.setValue(revenue.getQuarter(), revenueDTO.getQuarter()));

		revenue.setRevenueAmount(ControllerUtils.setValue(revenue.getRevenueAmount(), revenueDTO.getRevenueAmount()));



        revenue = revenueDao.save(revenue);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RevenueDTO getRevenueDTOById(Integer revenueId) {
	
		Revenue revenue = revenueDao.getById(revenueId);
			
		
		RevenueConvertCriteriaDTO convertCriteria = new RevenueConvertCriteriaDTO();
		return(this.convertRevenueToRevenueDTO(revenue,convertCriteria));
	}







}
