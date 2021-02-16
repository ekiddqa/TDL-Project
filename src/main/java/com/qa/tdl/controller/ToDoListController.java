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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdl.dto.ToDoListDTO;
import com.qa.tdl.persistence.domain.ToDoList;
import com.qa.tdl.service.ToDoListService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/toDoList")
public class ToDoListController {
	
	private ToDoListService service;
	
	@Autowired
	public ToDoListController(ToDoListService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<ToDoListDTO> create(@RequestBody ToDoList toDoList) {
		ToDoListDTO created = this.service.create(toDoList);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<ToDoListDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoListDTO> readById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoListDTO> update(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO){
		return new ResponseEntity<>(this.service.update(toDoListDTO, id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ToDoListDTO> delete(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO){
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
