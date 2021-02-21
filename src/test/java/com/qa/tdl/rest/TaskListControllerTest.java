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

import com.qa.tdl.controller.TaskListController;
import com.qa.tdl.dto.TaskListDTO;
import com.qa.tdl.persistence.domain.TaskList;
import com.qa.tdl.service.TaskListService;

@SpringBootTest
@ActiveProfiles("dev")
public class TaskListControllerTest {
	
	@Autowired
	private TaskListController controller;

	@MockBean
	private TaskListService service;

	@Autowired
	private ModelMapper mapper;

	private TaskListDTO mapToDTO(TaskList taskList) {
		return this.mapper.map(taskList, TaskListDTO.class);
	}

	private final TaskList TEST_LIST_1 = new TaskList(1L, "Chores", new ArrayList<>());
	private final TaskList TEST_LIST_2 = new TaskList(2L, "Admin", new ArrayList<>());
	
	private final List<TaskList> LISTOFTASKSLISTS = List.of(TEST_LIST_1, TEST_LIST_2);

	@Test
	void testCreate() throws Exception {
		when(this.service.create(TEST_LIST_1)).thenReturn(this.mapToDTO(TEST_LIST_1));
		assertThat(new ResponseEntity<TaskListDTO>(this.mapToDTO(TEST_LIST_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_LIST_1));
		verify(this.service, atLeastOnce()).create(TEST_LIST_1);
	}
	
	@Test
	void testReadById() throws Exception {
		when(this.service.readById(2L)).thenReturn(this.mapToDTO(TEST_LIST_2));
		assertThat(new ResponseEntity<TaskListDTO>(this.mapToDTO(TEST_LIST_2), HttpStatus.OK))
				.isEqualTo(this.controller.readById(2L));
		verify(this.service, atLeastOnce()).readById(2L);
	}

	@Test
	void testReadAll() throws Exception {
		List<TaskListDTO> LISTOFTASKSLISTSDTO = List.of(mapToDTO(TEST_LIST_1), mapToDTO(TEST_LIST_2));
		
		when(this.service.readAll()).thenReturn(LISTOFTASKSLISTSDTO);
		assertThat(new ResponseEntity<List<TaskListDTO>>(LISTOFTASKSLISTSDTO, HttpStatus.OK))
				.isEqualTo(this.controller.readAll());
		verify(this.service, atLeastOnce()).readAll();
	}
	
	@Test
	void testUpdate() throws Exception {
		when(this.service.update(1L, null)).thenReturn(this.mapToDTO(TEST_LIST_1));
		assertThat(new ResponseEntity<TaskListDTO>(this.mapToDTO(TEST_LIST_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(1L, null));
		verify(this.service, atLeastOnce()).update(1L, null);
	}
	
	@Test
	void testDelete() throws Exception {
		when(this.service.delete(1L)).thenReturn(true);
		assertThat(new ResponseEntity<TaskListDTO>(HttpStatus.NO_CONTENT))
				.isEqualTo(this.controller.delete(1L));
		verify(this.service, atLeastOnce()).delete(1L);
	}
	
	@Test
	void deleteInternalServerErrorTest() throws Exception {
		when(this.service.delete(999999L)).thenReturn(false);
		assertThat(new ResponseEntity<TaskListDTO>(HttpStatus.INTERNAL_SERVER_ERROR))
				.isEqualTo(this.controller.delete(999999L));
		verify(this.service, atLeastOnce()).delete(999999L);
	}
}
