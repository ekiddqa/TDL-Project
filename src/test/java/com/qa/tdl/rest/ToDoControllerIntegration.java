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
import com.qa.tdl.dto.ToDoDTO;
import com.qa.tdl.persistence.domain.ToDo;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:TDL-schema.sql",
		"classpath:TDL-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class ToDoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	private ModelMapper mapper = new ModelMapper();

	private ToDoDTO mapToDTO(ToDo toDo) {
		return this.mapper.map(toDo, ToDoDTO.class);
	}
	
	private ToDo testTask1 = new ToDo(1L, "Laundry", null);
	private ToDo testTask2 = new ToDo(2L, "Grocery Shopping", null);
	private ToDo testTask3 = new ToDo(3L, "Tidy house", null);
	private ToDo testTask4 = new ToDo(4L, "Reorganise bills into alphabetical statments", null);
	private final ToDo testTask5 = new ToDo(5L, "Meet Barbara", null);
	List<ToDo> choresList = List.of(testTask1, testTask2, testTask3);
	List<ToDo> adminList = List.of(testTask4);

	private final String URI = "/toDo";

	
	 @Test
	    void createIntegrationTest() throws Exception {

		 	ToDo createToDo = new ToDo(6L, "Some stuff", null);
	        ToDoDTO testSavedDTO = mapToDTO(createToDo); 
	        String TestSavedDTOAsJson = this.jsonifier.writeValueAsString(testSavedDTO);

	        RequestBuilder request = post(URI + "/create")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(createToDo));
	        
	        ResultMatcher checkStatus = status().isCreated();
	        ResultMatcher checkBody = content().json(TestSavedDTOAsJson);
	       
	        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	    }
	 
	 @Test
	    void readAllIntegrationTest() throws Exception {

	        List<ToDoDTO> testSavedListDTO = List.of(mapToDTO(testTask1), mapToDTO(testTask2), mapToDTO(testTask3), mapToDTO(testTask4), mapToDTO(testTask5)); 
	        
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

		 ToDoDTO testSavedDTO = mapToDTO(testTask2);    
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

		 ToDoDTO testSavedDTO = mapToDTO(testTask1);    
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

		 ToDoDTO testSavedDTO = mapToDTO(testTask1);    
		
	        RequestBuilder request = delete(URI + "/delete/1")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(this.jsonifier.writeValueAsString(testSavedDTO));
	         
	        ResultMatcher checkStatus = status().isNoContent();
	        
	        this.mvc.perform(request).andExpect(checkStatus);
	    }


}
