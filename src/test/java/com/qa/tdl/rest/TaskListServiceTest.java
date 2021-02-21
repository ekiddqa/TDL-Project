package com.qa.tdl.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdl.dto.TaskListDTO;
import com.qa.tdl.persistence.domain.TaskList;
import com.qa.tdl.persistence.repo.TaskListRepo;
import com.qa.tdl.service.TaskListService;

@SpringBootTest
public class TaskListServiceTest {
	
	@Autowired
	private TaskListService service;
	
	@MockBean
	private TaskListRepo repo;
	
	@MockBean
	private ModelMapper mapper;
	
	private TaskListDTO mapToDTO(TaskList taskList) {
		return this.mapper.map(taskList, TaskListDTO.class);
	}
	
	private final TaskList TEST_LIST_1 = new TaskList("Test List 1", null);
	private final TaskList TEST_LIST_2 = new TaskList("Test List 2", null);
	

	
	@Test
	void testCreate() {
		TaskList testCreateList = new TaskList("Dummy", null);
		TaskList testCreatedList = new TaskList(3L, "Dummy", null);
		when(this.repo.save(testCreateList)).thenReturn(testCreatedList);
		assertThat(this.service.create(testCreateList))
				.isEqualTo(mapToDTO(testCreatedList));
		verify(this.repo, times(1)).save(testCreateList);
	}
	
	@Test
	@Disabled
	void testReadAll() {
		final List<TaskList> LISTOFTASKSLISTS = List.of(TEST_LIST_1, TEST_LIST_2);
		List<TaskListDTO> LISTOFTASKSLISTSDTO = List.of(mapToDTO(TEST_LIST_1), mapToDTO(TEST_LIST_2));
		when(this.repo.findAll().thenReturn(LISTOFTASKSLISTS),
		assertThat(this.service.readAll()))
				.isEqualTo(LISTOFTASKSLISTSDTO);
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	@Disabled
	void testReadById() {
		Optional<TaskList> y = Optional.of(this.TEST_LIST_2);
		when(this.repo.findById(1L)).thenReturn(y);
		assertThat(Optional.of(mapToTaskList(this.service.readById(1L))))
				.isEqualTo((this.repo.findById(1L)));
		verify(this.repo, atLeastOnce()).findById(1L);
	}
	
	
	@Test
	@Disabled
	void testDelete() {
		when(this.repo.deleteById(2L)).thenReturn(true);
		assertThat(this.service.delete(2L))
				.isEqualTo(this.repo.deleteById(2L));
		verify(this.repo, atLeastOnce()).deleteById(2L);
	}
}
