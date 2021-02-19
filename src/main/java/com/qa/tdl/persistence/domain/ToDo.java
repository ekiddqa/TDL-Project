package com.qa.tdl.persistence.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String task;

    @NotNull
    private boolean complete;
    
	
    @ManyToOne
    private TaskList taskList = null;


	public ToDo(@NotNull String task, @NotNull boolean complete, TaskList taskList) {
		super();
		this.task = task;
		this.complete = complete;
		this.taskList = taskList;
	}
    
    
	 
}
