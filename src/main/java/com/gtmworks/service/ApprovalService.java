package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Approval;
import com.gtmworks.dto.ApprovalDTO;
import com.gtmworks.dto.ApprovalSearchDTO;
import com.gtmworks.dto.ApprovalPageDTO;
import com.gtmworks.dto.ApprovalConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ApprovalService extends GenericService<Approval, Integer> {

	List<Approval> findAll();

	ResultDTO addApproval(ApprovalDTO approvalDTO, RequestDTO requestDTO);

	ResultDTO updateApproval(ApprovalDTO approvalDTO, RequestDTO requestDTO);

    Page<Approval> getAllApprovals(Pageable pageable);

    Page<Approval> getAllApprovals(Specification<Approval> spec, Pageable pageable);

	ResponseEntity<ApprovalPageDTO> getApprovals(ApprovalSearchDTO approvalSearchDTO);
	
	List<ApprovalDTO> convertApprovalsToApprovalDTOs(List<Approval> approvals, ApprovalConvertCriteriaDTO convertCriteria);

	ApprovalDTO getApprovalDTOById(Integer approvalId);







}





