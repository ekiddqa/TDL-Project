package com.qa.tdl.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class TaskListServiceCompare {
	
	@Autowired
	private TaskListService service;
	
	@MockBean
	private TaskListRepo repo;
	
	@MockBean
	private ModelMapper mapper;
	
	private TaskListDTO mapToTDLDto(TaskList taskList) {
		return this.mapper.map(taskList, TaskListDTO.class);
	}
	
	// class resources
	private final TaskList TEST_LIST_1 = new TaskList("Test List 1", null);
	private final TaskList TEST_LIST_2 = new TaskList("Test List 2", null);
	
	
	
	
	@Test
	public void testCreate() throws Exception{
		//resources
		TaskList toCreate = new TaskList("Newly created list", null);
		TaskList created = new TaskList(3L,"Newly created list", null);
		
		//rules
		when(this.repo.save(toCreate)).thenReturn(created);
		//actions
		
		//assertions
		assertThat(this.service.create(toCreate)).isEqualTo(this.mapToTDLDto(created));
		verify(this.repo,times(1)).save(toCreate);
	}
	
	@Test
	public void testReadAll() throws Exception{
		//re
		List<TaskList> testLists=List.of(TEST_LIST_1,TEST_LIST_2);
		List<TaskListDTO> testListsAsDtos= testLists.stream().map(this::mapToTDLDto).collect(Collectors.toList());
		//ru
		when(this.repo.findAll()).thenReturn(testLists);
		//a
		assertThat(this.service.readAll()).isEqualTo(testListsAsDtos);
		verify(this.repo,times(1)).findAll();
		}
	
	@Test
	public void testReadById() throws Exception{
		//re
		Long id=1L;
		//ru
		when(this.repo.findById(id)).thenReturn(Optional.of(TEST_LIST_1));
		//a
		assertThat(this.service.readById(id)).isEqualTo(this.mapToTDLDto(TEST_LIST_1));
		verify(this.repo,times(1)).findById(id);
		
	}
	
	@Test
	public void testDeleteFail() throws Exception{
		Long id=1L;
		//ru
		when(this.repo.existsById(id)).thenReturn(true);
		//a
		assertThat(this.service.delete(id)).isEqualTo(false);
		verify(this.repo,times(1)).existsById(id);
	}

}
