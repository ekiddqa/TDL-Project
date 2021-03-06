package com.qa.tdl.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.tdl.controller.ToDoController;
import com.qa.tdl.dto.ToDoDTO;
import com.qa.tdl.persistence.domain.TaskList;
import com.qa.tdl.persistence.domain.ToDo;
import com.qa.tdl.service.ToDoService;

@SpringBootTest
@ActiveProfiles("dev")
class ToDoControllerTest {
	
	@Autowired
	private ToDoController controller;

	@MockBean
	private ToDoService service;

	@Autowired
	private ModelMapper mapper;

	private ToDoDTO mapToDTO(ToDo ToDo) {
		return this.mapper.map(ToDo, ToDoDTO.class);
	}

	private final TaskList testList1 = new TaskList(1L, "Chores", new ArrayList<>());
	private final TaskList testList2 = new TaskList(2L, "Admin", new ArrayList<>());
	
	private final ToDo testTask1 = new ToDo(1L, "Laundry", testList1);
	private final ToDo testTask2 = new ToDo(2L, "Grocery Shopping", testList1);
	private final ToDo testTask3 = new ToDo(3L, "Tidy House", testList1);
	private final ToDo testTask4 = new ToDo(4L, "Reorganise bills into alphabetical statments", testList2);
	private final ToDo testTask5 = new ToDo(2L, "Meet Barbara", null);
	

	@Test
	void createTest() throws Exception {
		when(this.service.create(testTask1)).thenReturn(this.mapToDTO(testTask1));
		
		assertThat(new ResponseEntity<ToDoDTO>(this.mapToDTO(testTask1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(testTask1));
		
		verify(this.service, atLeastOnce()).create(testTask1);
	}
	
	@Test
	void readByIdTest() throws Exception {
		when(this.service.readById(2L)).thenReturn(this.mapToDTO(testTask2));
		
		assertThat(new ResponseEntity<ToDoDTO>(this.mapToDTO(testTask2), HttpStatus.OK))
				.isEqualTo(this.controller.readById(2L));
		
		verify(this.service, atLeastOnce()).readById(2L);
	}

	@Test
	void readAllTest() throws Exception {
		List<ToDoDTO> LISTOFTASKSLISTSDTO = List.of(mapToDTO(testTask1), mapToDTO(testTask2), mapToDTO(testTask3), mapToDTO(testTask4), mapToDTO(testTask5));
		
		when(this.service.readAll()).thenReturn(LISTOFTASKSLISTSDTO);
		
		assertThat(new ResponseEntity<List<ToDoDTO>>(LISTOFTASKSLISTSDTO, HttpStatus.OK))
				.isEqualTo(this.controller.readAll());
		
		verify(this.service, atLeastOnce()).readAll();
	}
	
	@Test
	void updateTest() throws Exception {
		when(this.service.update(1L, null)).thenReturn(this.mapToDTO(testTask1));
		
		assertThat(new ResponseEntity<ToDoDTO>(this.mapToDTO(testTask1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(1L, null));
		
		verify(this.service, atLeastOnce()).update(1L, null);
	}
	
	@Test
	void deleteTest() throws Exception {
		when(this.service.delete(1L)).thenReturn(true);
		assertThat(new ResponseEntity<ToDoDTO>(HttpStatus.NO_CONTENT))
				.isEqualTo(this.controller.delete(1L));
		verify(this.service, atLeastOnce()).delete(1L);
	}
	
	@Test
	void deleteInternalServerErrorTest() throws Exception {
		when(this.service.delete(999999L)).thenReturn(false);
		
		assertThat(new ResponseEntity<ToDoDTO>(HttpStatus.INTERNAL_SERVER_ERROR))
				.isEqualTo(this.controller.delete(999999L));
		
		verify(this.service, atLeastOnce()).delete(999999L);
	}
}
