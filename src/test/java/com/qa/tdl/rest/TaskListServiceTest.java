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
import org.yaml.snakeyaml.error.YAMLException;

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
	
	private final TaskList TEST_LIST_1 = new TaskList("Chores", null);
	private final TaskList TEST_LIST_2 = new TaskList("Admin", null);
	

	
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
	void testReadAll() {
		final List<TaskList> listOfTasks = List.of(TEST_LIST_1, TEST_LIST_2);
		List<TaskListDTO> listofTasksDTO = listOfTasks.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findAll()).thenReturn(listOfTasks);
		assertThat(this.service.readAll())
				.isEqualTo(listofTasksDTO);
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadById() {
		Optional<TaskList> y = Optional.of(this.TEST_LIST_1);
		when(this.repo.findById(1L)).thenReturn(y);
		assertThat(this.service.readById(1L))
				.isEqualTo(this.mapToDTO(TEST_LIST_1));
		verify(this.repo, atLeastOnce()).findById(1L);
	}
	
	@Test
	void testUpdate() {
		Optional<TaskList> x = Optional.of(this.TEST_LIST_1);
		TaskList y = new TaskList("Dummy", null);
		TaskListDTO yDTO = mapToDTO(y);
		TaskList z = new TaskList(1L, "Dummy", null);
		
		when(this.repo.findById(1L)).thenReturn(x);
		when(this.service.update(1L, mapToDTO(z)).getGroupName()).thenReturn(z.getGroupName());
		assertThat(this.service.update(1L, yDTO)).isEqualTo(mapToDTO(z));
		verify(this.repo, atLeastOnce()).save(z);
	}
	
	@Test
	void testDelete() {
		when(this.repo.existsById(2L)).thenReturn(false);
		assertThat(this.service.delete(2L))
				.isEqualTo(true);
		verify(this.repo, atLeastOnce()).deleteById(2L);
	}
	
	@Test
	void testDeleteFailure() {
		when(this.repo.existsById(1L)).thenReturn(true);
		assertThat(this.service.delete(1L))
				.isEqualTo(false);
		verify(this.repo, atLeastOnce()).deleteById(1L);
	}
}
