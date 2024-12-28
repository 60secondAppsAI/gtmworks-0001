package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Account;
import com.gtmworks.dto.AccountDTO;
import com.gtmworks.dto.AccountSearchDTO;
import com.gtmworks.dto.AccountPageDTO;
import com.gtmworks.dto.AccountConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AccountService extends GenericService<Account, Integer> {

	List<Account> findAll();

	ResultDTO addAccount(AccountDTO accountDTO, RequestDTO requestDTO);

	ResultDTO updateAccount(AccountDTO accountDTO, RequestDTO requestDTO);

    Page<Account> getAllAccounts(Pageable pageable);

    Page<Account> getAllAccounts(Specification<Account> spec, Pageable pageable);

	ResponseEntity<AccountPageDTO> getAccounts(AccountSearchDTO accountSearchDTO);
	
	List<AccountDTO> convertAccountsToAccountDTOs(List<Account> accounts, AccountConvertCriteriaDTO convertCriteria);

	AccountDTO getAccountDTOById(Integer accountId);







}





