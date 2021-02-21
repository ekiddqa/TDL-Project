package com.qa.tdl.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdl.dto.ToDoDTO;
import com.qa.tdl.persistence.domain.TaskList;
import com.qa.tdl.persistence.domain.ToDo;
import com.qa.tdl.persistence.repo.ToDoRepo;
import com.qa.tdl.service.TaskListService;
import com.qa.tdl.service.ToDoService;

public class ToDoServiceTest {
	
	@Autowired
	private ToDoService service;
	
	@MockBean
	private ToDoRepo repo;
	
	@MockBean
	private ModelMapper mapper;
	
	static final ModelMapper testMapper = new ModelMapper();

	private ToDoDTO mapToDTO(ToDo toDo) {
		return this.mapper.map(toDo, ToDoDTO.class);
	}
	
	private ToDo mapToToDo(ToDoDTO toDoDTO) {
		return this.mapper.map(toDoDTO, ToDo.class);
	}
	
	private ToDo TEST_TASK_1 = new ToDo("Laundry");
	private ToDo TEST_SAVED_TASK_1 = new ToDo(1L,"Laundry");
	private ToDo TEST_TASK_2 = new ToDo(2L, "Grocery Shopping", null);
	//private Optional<ToDo> TEST_TASK_OP_2 = new ToDo(2L, "Grocery Shopping", null);
	private ToDo TEST_TASK_3 = new ToDo(3L, "Tidy house", null);
	private ToDo TEST_TASK_4 = new ToDo(4L, "Reorganise bills into alphabetical statments", null);
	private final ToDo TEST_TASK_5 = new ToDo(2L, "Meet Barbara", null);
	List<ToDo> choresList = List.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3);
	List<ToDo> adminList = List.of(TEST_TASK_4);
	
	private final TaskList TEST_LIST_1 = new TaskList(1L, "Chores", choresList);
	private final TaskList TEST_LIST_2 = new TaskList(2L, "Admin", adminList);
	
	private final List<TaskList> LISTOFTASKLISTS = List.of(TEST_LIST_1, TEST_LIST_2);
	private final List<ToDo> LISTOFTODOS = List.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3, TEST_TASK_4, TEST_TASK_5);
	
	@Test
	void testCreate() {
		when(this.repo.save(TEST_TASK_1)).thenReturn(TEST_SAVED_TASK_1);
		assertThat(mapToToDo(this.service.create(TEST_TASK_1)))
				.isEqualTo(TEST_SAVED_TASK_1);
		verify(this.repo, atLeastOnce()).save(TEST_TASK_1);
	}
	
	@Test
	void testReadAll() {
		when(this.repo.findAll().thenReturn(LISTOFTODOS),
		assertThat(this.service.readAll()))
				.isEqualTo(this.repo.findAll());
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadById() {
		when(this.repo.findById(1L)).thenReturn(TEST_TASK_OP_2);
		assertThat(mapToToDo(this.service.readById(1L)))
				.isEqualTo(this.repo.findById(1L));
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
