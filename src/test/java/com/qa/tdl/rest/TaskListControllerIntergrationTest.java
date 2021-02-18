package com.qa.tdl.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:TDL-schema.sql",
		"classpath:TDL-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TaskListControllerIntergrationTest {
	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	private ModelMapper mapper = new ModelMapper();

	private TaskListDTO mapToDTO(TaskList taskList) {
		return this.mapper.map(taskList, TaskListDTO.class);
	}

	private final TaskList TEST_TaskList_1 = new TaskList(1L, "Chores", new ArrayList<>());
	private final TaskList TEST_TaskList_2 = new TaskList(2L, "Admin", null);


	private final List<TaskList> LISTOFTASKLISTS = List.of(TEST_TaskList_1, TEST_TaskList_2);

	private final String URI = "/TaskList";

	
	 @Test
	    public void createIntegrationTest() throws Exception {
	        
	        // RESOURCES
	        TaskListDTO testSavedDTO = mapToDTO(TEST_TaskList_1); 
	        String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO); //response string
	        
	        // ACTIONS
	        RequestBuilder request = post(URI + "/create")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(TEST_TaskList_1));
	        
	        // ASSERTIONS
	        ResultMatcher checkStatus = status().isCreated();
	        ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
	        
	        //checkbody = 
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 
	 @Test
	    public void readAllIntegrationTest() throws Exception {
	        
	        List<TaskListDTO> testSavedListDTO = List.of(mapToDTO(TEST_TaskList_1), mapToDTO(TEST_TaskList_2)); 
	        
	        String testSavedListDTOAsJson = this.jsonifier.writeValueAsString(testSavedListDTO); //response string
	        
	        RequestBuilder request = get(URI + "/read")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedListDTO));
	         
	        ResultMatcher checkStatus = status().isOk();
	        ResultMatcher checkBody = content().json(testSavedListDTOAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 
	 @Test
	    public void readByIdIntegrationTest() throws Exception {

		 TaskListDTO testSavedDTO = mapToDTO(TEST_TaskList_2);    
		 String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO); //response string
	        
	        RequestBuilder request = get(URI + "/read/2")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedDTO));
	         
	        ResultMatcher checkStatus = status().isOk();
	        ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
	        
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
}
