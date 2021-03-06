package com.qa.tdl.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl.dto.ToDoDTO;
import com.qa.tdl.persistence.domain.ToDo;
import com.qa.tdl.service.ToDoService;


@RestController
@CrossOrigin
@RequestMapping("/toDo")
public class ToDoController {
	
	private ToDoService service;
	
	@Autowired
	public ToDoController(ToDoService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<ToDoDTO> create(@RequestBody ToDo toDo) {
		ToDoDTO created = this.service.create(toDo);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<ToDoDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoDTO> readById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoDTO> update(@PathVariable Long id, @RequestBody ToDoDTO toDoListDTO){
		return new ResponseEntity<>(this.service.update(id, toDoListDTO), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ToDoDTO> delete(@PathVariable Long id){
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
