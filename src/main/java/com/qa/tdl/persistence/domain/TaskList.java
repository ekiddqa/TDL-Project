package com.qa.tdl.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskList {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String groupName;
	
	@OneToMany(mappedBy = "taskList", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private List<ToDo> toDoTasks;
	
	public TaskList(String groupName, List<ToDo> toDoTasks) {
		super();
		this.groupName = groupName;
		this.toDoTasks = toDoTasks;
	}
	

}
