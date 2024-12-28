package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Contact;
import com.gtmworks.dto.ContactDTO;
import com.gtmworks.dto.ContactSearchDTO;
import com.gtmworks.dto.ContactPageDTO;
import com.gtmworks.dto.ContactConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ContactService extends GenericService<Contact, Integer> {

	List<Contact> findAll();

	ResultDTO addContact(ContactDTO contactDTO, RequestDTO requestDTO);

	ResultDTO updateContact(ContactDTO contactDTO, RequestDTO requestDTO);

    Page<Contact> getAllContacts(Pageable pageable);

    Page<Contact> getAllContacts(Specification<Contact> spec, Pageable pageable);

	ResponseEntity<ContactPageDTO> getContacts(ContactSearchDTO contactSearchDTO);
	
	List<ContactDTO> convertContactsToContactDTOs(List<Contact> contacts, ContactConvertCriteriaDTO convertCriteria);

	ContactDTO getContactDTOById(Integer contactId);







}





