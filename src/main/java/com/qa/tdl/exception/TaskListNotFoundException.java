package com.qa.tdl.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Group does not exist with that ID")
public class TaskListNotFoundException  extends EntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5589126668450989776L;

}
