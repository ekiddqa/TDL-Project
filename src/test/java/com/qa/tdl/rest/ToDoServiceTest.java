package com.qa.tdl.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
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
import com.qa.tdl.dto.ToDoDTO;
import com.qa.tdl.persistence.domain.ToDo;
import com.qa.tdl.persistence.repo.ToDoRepo;
import com.qa.tdl.service.ToDoService;

@SpringBootTest
public class ToDoServiceTest {
	
	@Autowired
	private ToDoService service;
	
	@MockBean
	private ToDoRepo repo;
	
	@MockBean
	private ModelMapper mapper;
	
	private ToDoDTO mapToDTO(ToDo toDo) {
		return this.mapper.map(toDo, ToDoDTO.class);
	}
	
	private ToDo TEST_TASK_1 = new ToDo(1L, "Laundry", null);
	private ToDo TEST_TASK_2 = new ToDo(2L, "Grocery Shopping", null);
	private ToDo TEST_TASK_3 = new ToDo(3L, "Tidy house", null);
	private ToDo TEST_TASK_4 = new ToDo(4L, "Reorganise bills into alphabetical statments", null);
	private final ToDo TEST_TASK_5 = new ToDo(2L, "Meet Barbara", null);
	
	List<ToDo> choresList = List.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3);
	List<ToDo> adminList = List.of(TEST_TASK_4);
	
	private final List<ToDo> LISTOFTODOS = List.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3, TEST_TASK_4, TEST_TASK_5);
	
	@Test
	void testCreate() {
		ToDo testCreateToDo = new ToDo("Dummy", null);
		ToDo testCreatedToDo = new ToDo(3L, "Dummy", null);
		when(this.repo.save(testCreateToDo)).thenReturn(testCreatedToDo);
		assertThat(this.service.create(testCreateToDo))
				.isEqualTo(mapToDTO(testCreatedToDo));
		verify(this.repo, times(1)).save(testCreateToDo);
	}
	
	@Test
	void testReadAll() {
		List<ToDoDTO> listofTasksDTO = LISTOFTODOS.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.repo.findAll()).thenReturn(LISTOFTODOS);
		assertThat(this.service.readAll())
				.isEqualTo(listofTasksDTO);
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadById() {
		Optional<ToDo> y = Optional.of(this.TEST_TASK_1);
		when(this.repo.findById(1L)).thenReturn(y);
		assertThat(this.service.readById(1L))
				.isEqualTo(this.mapToDTO(TEST_TASK_1));
		verify(this.repo, atLeastOnce()).findById(1L);
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
