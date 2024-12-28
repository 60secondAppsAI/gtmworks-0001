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
import com.gtmworks.dao.TaskDAO;
import com.gtmworks.domain.Task;
import com.gtmworks.dto.TaskDTO;
import com.gtmworks.dto.TaskSearchDTO;
import com.gtmworks.dto.TaskPageDTO;
import com.gtmworks.dto.TaskConvertCriteriaDTO;
import com.gtmworks.dto.common.RequestDTO;
import com.gtmworks.dto.common.ResultDTO;
import com.gtmworks.service.TaskService;
import com.gtmworks.util.ControllerUtils;





@Service
public class TaskServiceImpl extends GenericServiceImpl<Task, Integer> implements TaskService {

    private final static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Autowired
	TaskDAO taskDao;

	


	@Override
	public GenericDAO<Task, Integer> getDAO() {
		return (GenericDAO<Task, Integer>) taskDao;
	}
	
	public List<Task> findAll () {
		List<Task> tasks = taskDao.findAll();
		
		return tasks;	
		
	}

	public ResultDTO addTask(TaskDTO taskDTO, RequestDTO requestDTO) {

		Task task = new Task();

		task.setTaskId(taskDTO.getTaskId());


		task.setSubject(taskDTO.getSubject());


		task.setDueDate(taskDTO.getDueDate());


		task.setStatus(taskDTO.getStatus());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		task = taskDao.save(task);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Task> getAllTasks(Pageable pageable) {
		return taskDao.findAll(pageable);
	}

	public Page<Task> getAllTasks(Specification<Task> spec, Pageable pageable) {
		return taskDao.findAll(spec, pageable);
	}

	public ResponseEntity<TaskPageDTO> getTasks(TaskSearchDTO taskSearchDTO) {
	
			Integer taskId = taskSearchDTO.getTaskId(); 
 			String subject = taskSearchDTO.getSubject(); 
   			String status = taskSearchDTO.getStatus(); 
 			String sortBy = taskSearchDTO.getSortBy();
			String sortOrder = taskSearchDTO.getSortOrder();
			String searchQuery = taskSearchDTO.getSearchQuery();
			Integer page = taskSearchDTO.getPage();
			Integer size = taskSearchDTO.getSize();

	        Specification<Task> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, taskId, "taskId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, subject, "subject"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("subject")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Task> tasks = this.getAllTasks(spec, pageable);
		
		//System.out.println(String.valueOf(tasks.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(tasks.getTotalPages()));
		
		List<Task> tasksList = tasks.getContent();
		
		TaskConvertCriteriaDTO convertCriteria = new TaskConvertCriteriaDTO();
		List<TaskDTO> taskDTOs = this.convertTasksToTaskDTOs(tasksList,convertCriteria);
		
		TaskPageDTO taskPageDTO = new TaskPageDTO();
		taskPageDTO.setTasks(taskDTOs);
		taskPageDTO.setTotalElements(tasks.getTotalElements());
		return ResponseEntity.ok(taskPageDTO);
	}

	public List<TaskDTO> convertTasksToTaskDTOs(List<Task> tasks, TaskConvertCriteriaDTO convertCriteria) {
		
		List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();
		
		for (Task task : tasks) {
			taskDTOs.add(convertTaskToTaskDTO(task,convertCriteria));
		}
		
		return taskDTOs;

	}
	
	public TaskDTO convertTaskToTaskDTO(Task task, TaskConvertCriteriaDTO convertCriteria) {
		
		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setTaskId(task.getTaskId());

	
		taskDTO.setSubject(task.getSubject());

	
		taskDTO.setDueDate(task.getDueDate());

	
		taskDTO.setStatus(task.getStatus());

	

		
		return taskDTO;
	}

	public ResultDTO updateTask(TaskDTO taskDTO, RequestDTO requestDTO) {
		
		Task task = taskDao.getById(taskDTO.getTaskId());

		task.setTaskId(ControllerUtils.setValue(task.getTaskId(), taskDTO.getTaskId()));

		task.setSubject(ControllerUtils.setValue(task.getSubject(), taskDTO.getSubject()));

		task.setDueDate(ControllerUtils.setValue(task.getDueDate(), taskDTO.getDueDate()));

		task.setStatus(ControllerUtils.setValue(task.getStatus(), taskDTO.getStatus()));



        task = taskDao.save(task);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TaskDTO getTaskDTOById(Integer taskId) {
	
		Task task = taskDao.getById(taskId);
			
		
		TaskConvertCriteriaDTO convertCriteria = new TaskConvertCriteriaDTO();
		return(this.convertTaskToTaskDTO(task,convertCriteria));
	}







}
