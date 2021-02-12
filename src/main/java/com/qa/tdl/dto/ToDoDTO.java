package com.qa.tdl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ToDoDTO {
	
	private Long id;
	private String task;
    private boolean completed;



}
