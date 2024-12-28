package com.gtmworks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.gtmworks.domain.Task;
import com.gtmworks.dto.TaskDTO;
import com.gtmworks.dto.TaskSearchDTO;
import com.gtmworks.dto.TaskPageDTO;
import com.gtmworks.dto.TaskConvertCriteriaDTO;
import com.gtmworks.service.GenericService;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TaskService extends GenericService<Task, Integer> {

	List<Task> findAll();

	ResultDTO addTask(TaskDTO taskDTO, RequestDTO requestDTO);

	ResultDTO updateTask(TaskDTO taskDTO, RequestDTO requestDTO);

    Page<Task> getAllTasks(Pageable pageable);

    Page<Task> getAllTasks(Specification<Task> spec, Pageable pageable);

	ResponseEntity<TaskPageDTO> getTasks(TaskSearchDTO taskSearchDTO);
	
	List<TaskDTO> convertTasksToTaskDTOs(List<Task> tasks, TaskConvertCriteriaDTO convertCriteria);

	TaskDTO getTaskDTOById(Integer taskId);







}





