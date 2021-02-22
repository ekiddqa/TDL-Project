package com.qa.tdl.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component

public class TaskListDTO {
	
	private Long id;
	private String groupName;
	
	private List<ToDoDTO> toDoTasks = new ArrayList<>();

}
