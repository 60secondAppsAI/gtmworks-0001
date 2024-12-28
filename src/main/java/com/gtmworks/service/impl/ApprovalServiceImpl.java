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
import com.gtmworks.dao.ApprovalDAO;
import com.gtmworks.domain.Approval;
import com.gtmworks.dto.ApprovalDTO;
import com.gtmworks.dto.ApprovalSearchDTO;
import com.gtmworks.dto.ApprovalPageDTO;
import com.gtmworks.dto.ApprovalConvertCriteriaDTO;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import com.gtmworks.service.ApprovalService;
import com.gtmworks.util.ControllerUtils;





@Service
public class ApprovalServiceImpl extends GenericServiceImpl<Approval, Integer> implements ApprovalService {

    private final static Logger logger = LoggerFactory.getLogger(ApprovalServiceImpl.class);

	@Autowired
	ApprovalDAO approvalDao;

	


	@Override
	public GenericDAO<Approval, Integer> getDAO() {
		return (GenericDAO<Approval, Integer>) approvalDao;
	}
	
	public List<Approval> findAll () {
		List<Approval> approvals = approvalDao.findAll();
		
		return approvals;	
		
	}

	public ResultDTO addApproval(ApprovalDTO approvalDTO, RequestDTO requestDTO) {

		Approval approval = new Approval();

		approval.setApprovalId(approvalDTO.getApprovalId());


		approval.setApprovalType(approvalDTO.getApprovalType());


		approval.setStatus(approvalDTO.getStatus());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		approval = approvalDao.save(approval);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Approval> getAllApprovals(Pageable pageable) {
		return approvalDao.findAll(pageable);
	}

	public Page<Approval> getAllApprovals(Specification<Approval> spec, Pageable pageable) {
		return approvalDao.findAll(spec, pageable);
	}

	public ResponseEntity<ApprovalPageDTO> getApprovals(ApprovalSearchDTO approvalSearchDTO) {
	
			Integer approvalId = approvalSearchDTO.getApprovalId(); 
 			String approvalType = approvalSearchDTO.getApprovalType(); 
 			String status = approvalSearchDTO.getStatus(); 
 			String sortBy = approvalSearchDTO.getSortBy();
			String sortOrder = approvalSearchDTO.getSortOrder();
			String searchQuery = approvalSearchDTO.getSearchQuery();
			Integer page = approvalSearchDTO.getPage();
			Integer size = approvalSearchDTO.getSize();

	        Specification<Approval> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, approvalId, "approvalId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, approvalType, "approvalType"); 
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("approvalType")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Approval> approvals = this.getAllApprovals(spec, pageable);
		
		//System.out.println(String.valueOf(approvals.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(approvals.getTotalPages()));
		
		List<Approval> approvalsList = approvals.getContent();
		
		ApprovalConvertCriteriaDTO convertCriteria = new ApprovalConvertCriteriaDTO();
		List<ApprovalDTO> approvalDTOs = this.convertApprovalsToApprovalDTOs(approvalsList,convertCriteria);
		
		ApprovalPageDTO approvalPageDTO = new ApprovalPageDTO();
		approvalPageDTO.setApprovals(approvalDTOs);
		approvalPageDTO.setTotalElements(approvals.getTotalElements());
		return ResponseEntity.ok(approvalPageDTO);
	}

	public List<ApprovalDTO> convertApprovalsToApprovalDTOs(List<Approval> approvals, ApprovalConvertCriteriaDTO convertCriteria) {
		
		List<ApprovalDTO> approvalDTOs = new ArrayList<ApprovalDTO>();
		
		for (Approval approval : approvals) {
			approvalDTOs.add(convertApprovalToApprovalDTO(approval,convertCriteria));
		}
		
		return approvalDTOs;

	}
	
	public ApprovalDTO convertApprovalToApprovalDTO(Approval approval, ApprovalConvertCriteriaDTO convertCriteria) {
		
		ApprovalDTO approvalDTO = new ApprovalDTO();
		
		approvalDTO.setApprovalId(approval.getApprovalId());

	
		approvalDTO.setApprovalType(approval.getApprovalType());

	
		approvalDTO.setStatus(approval.getStatus());

	

		
		return approvalDTO;
	}

	public ResultDTO updateApproval(ApprovalDTO approvalDTO, RequestDTO requestDTO) {
		
		Approval approval = approvalDao.getById(approvalDTO.getApprovalId());

		approval.setApprovalId(ControllerUtils.setValue(approval.getApprovalId(), approvalDTO.getApprovalId()));

		approval.setApprovalType(ControllerUtils.setValue(approval.getApprovalType(), approvalDTO.getApprovalType()));

		approval.setStatus(ControllerUtils.setValue(approval.getStatus(), approvalDTO.getStatus()));



        approval = approvalDao.save(approval);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ApprovalDTO getApprovalDTOById(Integer approvalId) {
	
		Approval approval = approvalDao.getById(approvalId);
			
		
		ApprovalConvertCriteriaDTO convertCriteria = new ApprovalConvertCriteriaDTO();
		return(this.convertApprovalToApprovalDTO(approval,convertCriteria));
	}







}
