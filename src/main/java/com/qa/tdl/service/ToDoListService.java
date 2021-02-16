package com.qa.tdl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.dto.ToDoListDTO;
import com.qa.tdl.persistence.domain.ToDoList;
import com.qa.tdl.persistence.repo.ToDoListRepo;
import com.qa.tdl.utilis.SpringBean;

@Service
public class ToDoListService {

	//Access to repo - Interfaces
	private ToDoListRepo repo;

	//Maps to DTO/domain
	private ModelMapper mapper;

	private ToDoListDTO mapToDTO(ToDoList toDoList) {
		return this.mapper.map(toDoList, ToDoListDTO.class);
	}

	@Autowired
	public ToDoListService(ToDoListRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}


	public ToDoListDTO create(ToDoList toDoList) {
		return this.mapToDTO(this.repo.save(toDoList));
	}


	public List<ToDoListDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		
	}


	public ToDoListDTO readById(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(ToDoListNotFoundException::new));
	}


	public ToDoListDTO update(ToDoListDTO toDoListDTO, Long id) {
		
		ToDoList toUpdate = this.repo.findById(id).orElseThrow(ToDoListNotFoundException::new);	
		toUpdate.setName(toDoListDTO.getGroupName());	
		SpringBean.mergeNotNull(toDoListDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
		}


	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
		}

}


