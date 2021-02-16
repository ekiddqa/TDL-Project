package com.qa.tdl.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class TaskListDTO {
	
	private Long id;
	private String groupName;
	
	private List<ToDoDTO> toDoTasks = new ArrayList<>();

}
