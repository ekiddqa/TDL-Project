package com.qa.tdl.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdl.dto.ToDoDTO;
import com.qa.tdl.exception.ToDoNotFoundException;
import com.qa.tdl.persistence.domain.ToDo;
import com.qa.tdl.persistence.repo.ToDoRepo;
import com.qa.tdl.utilis.SpringBean;

@Service
public class ToDoService {
	
	private ToDoRepo repo;
	
	private ModelMapper mapper;
	
	private ToDoDTO mapToDTO(ToDo todo) {
	return this.mapper.map(todo, ToDoDTO.class);
	}
	
	@Autowired
	public ToDoService(ToDoRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	

	 public ToDoDTO create(ToDo todo) { 
	    return this.mapToDTO(this.repo.save(todo)); }

	
     public List<ToDoDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());

}

	public ToDoDTO readById(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(ToDoNotFoundException::new));
	}


	public ToDoDTO update(ToDoDTO toDoDTO, Long id) {
		
		ToDo toUpdate = this.repo.findById(id).orElseThrow(ToDoNotFoundException::new);
		toUpdate.setTask(toDoDTO.getTask());
		SpringBean.mergeNotNull(toDoDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}


	public boolean delete(Long id) {
		this.repo.deleteById(id);//
		return !this.repo.existsById(id);//
	}
}
