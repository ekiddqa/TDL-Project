package com.qa.tdl.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.tdl.dto.TaskListDTO;
import com.qa.tdl.persistence.domain.TaskList;
import com.qa.tdl.persistence.domain.ToDo;
import com.qa.tdl.persistence.repo.TaskListRepo;
import com.qa.tdl.service.TaskListService;

@SpringBootTest
public class TaskListServiceTest {
	
	@Autowired
	private TaskListService service;
	
	@MockBean
	private TaskListRepo repo;
	
	@Autowired
	private ModelMapper mapper;
	
	private TaskList mapToTaskList(TaskListDTO taskListDTO) {
		return this.mapper.map(taskListDTO, TaskList.class);
	}
	
	private TaskListDTO mapToDTO(TaskList taskList) {
		return this.mapper.map(taskList, TaskListDTO.class);
	}
	
	private final TaskList TEST_LIST_1 = new TaskList(null, "Chores", new ArrayList<>());
	private final TaskList TEST_SAVED_LIST_1 = new TaskList(1L, "Chores", new ArrayList<>());
	private final TaskList TEST_LIST_2 = new TaskList(2L, "Admin", new ArrayList<>());
	
	private final List<TaskList> LISTOFTASKSLISTS = List.of(TEST_LIST_1, TEST_LIST_2);
	List<TaskListDTO> LISTOFTASKSLISTSDTO = List.of(mapToDTO(TEST_LIST_1), mapToDTO(TEST_LIST_2));
	
	@Test
	void testCreate() {
		when(this.repo.save(TEST_LIST_1)).thenReturn(TEST_SAVED_LIST_1);
		assertThat(mapToTaskList(this.service.create(TEST_LIST_1)))
				.isEqualTo(TEST_SAVED_LIST_1);
		verify(this.repo, atLeastOnce()).save(TEST_LIST_1);
	}
	
	@Test
	void testReadAll() {
		when(this.repo.findAll()).thenReturn(LISTOFTASKSLISTS);
		assertThat(this.service.readAll())
				.isEqualTo(LISTOFTASKSLISTSDTO);
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadById() {
		Optional<TaskList> y = Optional.of(this.TEST_LIST_2);
		when(this.repo.findById(1L)).thenReturn(y);
		assertThat(Optional.of(mapToTaskList(this.service.readById(1L))))
				.isEqualTo((this.repo.findById(1L)));
		verify(this.repo, atLeastOnce()).findById(1L);
	}
	
	
	@Test
	void testDelete() {
		when(this.repo.deleteById(2L)).thenReturn(true);
		assertThat(this.service.delete(2L))
				.isEqualTo(this.repo.deleteById(2L));
		verify(this.repo, atLeastOnce()).deleteById(2L);
	}
}
