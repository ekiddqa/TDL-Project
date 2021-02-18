package com.qa.tdl.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
public class TaskListTest {
	
	@Autowired
	private TaskListController controller;

	@MockBean
	private TaskListService service;

	@Autowired
	private ModelMapper mapper;

	private TaskListDTO mapToDTO(TaskList taskList) {
		return this.mapper.map(taskList, TaskListDTO.class);
	}

	private final TaskList TEST_LIST_1 = new TaskList(1L, "Chores", null);
	private final TaskList TEST_LIST_2 = new TaskList(2L, "Admin", null);
	
	private final List<TaskList> LISTOFTASKSLISTS = List.of(TEST_LIST_1, TEST_LIST_2);

	@Test
	void createTest() throws Exception {
		when(this.service.create(TEST_LIST_1)).thenReturn(this.mapToDTO(TEST_LIST_1));
		assertThat(new ResponseEntity<TaskListDTO>(this.mapToDTO(TEST_LIST_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_LIST_1));
		verify(this.service, atLeastOnce()).create(TEST_LIST_1);
	}
	
	@Test
	void readByIdTest() throws Exception {
		when(this.service.readById(2L)).thenReturn(this.mapToDTO(TEST_LIST_2));
		assertThat(new ResponseEntity<TaskListDTO>(this.mapToDTO(TEST_LIST_2), HttpStatus.OK))
				.isEqualTo(this.controller.readById(2L));
		verify(this.service, atLeastOnce()).readById(2L);
	}

	@Test
	void updateTest() throws Exception {
		when(this.service.update(null, 1L)).thenReturn(this.mapToDTO(TEST_LIST_1));
		assertThat(new ResponseEntity<TaskListDTO>(this.mapToDTO(TEST_LIST_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(1L, null));
		verify(this.service, atLeastOnce()).update(null, 1L);
	}
}
