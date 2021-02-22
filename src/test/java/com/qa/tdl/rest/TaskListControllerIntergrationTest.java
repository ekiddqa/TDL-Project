package com.qa.tdl.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tdl.dto.TaskListDTO;
import com.qa.tdl.persistence.domain.TaskList;
import com.qa.tdl.persistence.domain.ToDo;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:TDL-schema.sql",
		"classpath:TDL-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class TaskListControllerIntergrationTest {
	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	private ModelMapper mapper = new ModelMapper();

	private TaskListDTO mapToDTO(TaskList taskList) {
		return this.mapper.map(taskList, TaskListDTO.class);
	}
	
	private ToDo testTask1 = new ToDo(1L, "Laundry", null);
	private ToDo testTask2 = new ToDo(2L, "Grocery Shopping", null);
	private ToDo testTask3 = new ToDo(3L, "Tidy house", null);
	private ToDo testTask4 = new ToDo(4L, "Reorganise bills into alphabetical statments", null);
	List<ToDo> choresList = List.of(testTask1, testTask2, testTask3);
	List<ToDo> adminList = List.of(testTask4);
	
	private final TaskList testList1 = new TaskList(1L, "Chores", choresList);
	private final TaskList testList2 = new TaskList(2L, "Admin", adminList);

	private final String URI = "/taskList";

	
	 @Test
	    void createIntegrationTest() throws Exception {       
	        TaskListDTO testSavedDTO = mapToDTO(testList1); 
	        String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO);
	        
	        RequestBuilder request = post(URI + "/create")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testList1));
	        
	        ResultMatcher checkStatus = status().isCreated();
	        ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 
	 @Test
	    void readAllIntegrationTest() throws Exception {
	        List<TaskListDTO> testSavedListDTO = List.of(mapToDTO(testList1), mapToDTO(testList2)); 
	        
	        String testSavedListAsJson = this.jsonifier.writeValueAsString(testSavedListDTO);
	        
	        RequestBuilder request = get(URI + "/read")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedListDTO));
	         
	        ResultMatcher checkStatus = status().isOk();
	        ResultMatcher checkBody = content().json(testSavedListAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 
	 @Test
	    void readByIdIntegrationTest() throws Exception {

		 TaskListDTO testSavedDTO = mapToDTO(testList2);    
		 String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO);
	        
	        RequestBuilder request = get(URI + "/read/2")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedDTO));
	         
	        ResultMatcher checkStatus = status().isOk();
	        ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 @Test
	    void updateIntegrationTest() throws Exception {

		 TaskListDTO testSavedDTO = mapToDTO(testList1);    
		 String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO);
	        
	        RequestBuilder request = put(URI + "/update/1")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedDTO));
	         
	        ResultMatcher checkStatus = status().isAccepted();
	        ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 
	 @Test
	    void deleteIntegrationTest() throws Exception {

		 TaskListDTO testSavedDTO = mapToDTO(testList1);    
		
	        RequestBuilder request = delete(URI + "/delete/1")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedDTO));
	         
	        ResultMatcher checkStatus = status().isNoContent();
	        
	        this.mvc.perform(request).andExpect(checkStatus);
	    }

}
