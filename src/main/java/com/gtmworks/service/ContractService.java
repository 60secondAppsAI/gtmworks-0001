package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Contract;
import com.gtmworks.dto.ContractDTO;
import com.gtmworks.dto.ContractSearchDTO;
import com.gtmworks.dto.ContractPageDTO;
import com.gtmworks.dto.ContractConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ContractService extends GenericService<Contract, Integer> {

	List<Contract> findAll();

	ResultDTO addContract(ContractDTO contractDTO, RequestDTO requestDTO);

	ResultDTO updateContract(ContractDTO contractDTO, RequestDTO requestDTO);

    Page<Contract> getAllContracts(Pageable pageable);

    Page<Contract> getAllContracts(Specification<Contract> spec, Pageable pageable);

	ResponseEntity<ContractPageDTO> getContracts(ContractSearchDTO contractSearchDTO);
	
	List<ContractDTO> convertContractsToContractDTOs(List<Contract> contracts, ContractConvertCriteriaDTO convertCriteria);

	ContractDTO getContractDTOById(Integer contractId);







}





