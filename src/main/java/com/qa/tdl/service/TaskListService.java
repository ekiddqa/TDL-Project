package com.qa.tdl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.dto.TaskListDTO;
import com.qa.tdl.exception.TaskListNotFoundException;
import com.qa.tdl.persistence.domain.TaskList;
import com.qa.tdl.persistence.repo.TaskListRepo;
import com.qa.tdl.utilis.SpringBean;

@Service
public class TaskListService {

	//Access to repo - Interfaces
	private TaskListRepo repo;

	//Maps to DTO/domain
	private ModelMapper mapper;

	//Turns TaskList to DTO's
	private TaskListDTO mapToDTO(TaskList taskList) {
		return this.mapper.map(taskList, TaskListDTO.class);
	}
	
	//DTOs to TaskList
	private TaskList mapToTaskList(TaskListDTO taskListDTO) {
		return this.mapper.map(taskListDTO, TaskList.class);
	}

	
	@Autowired
	public TaskListService(TaskListRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}


	public TaskListDTO create(TaskList taskList) {
		return this.mapToDTO(this.repo.save(taskList));
	}


	public List<TaskListDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		
	}


	public TaskListDTO readById(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(TaskListNotFoundException::new));
	}


	public TaskListDTO update(Long id, TaskListDTO taskListDTO) {
		
		TaskList toUpdate = this.repo.findById(id).orElseThrow(TaskListNotFoundException::new);	
		toUpdate.setGroupName(taskListDTO.getGroupName());
		SpringBean.mergeNotNull(taskListDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
		}


	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
		}

}


