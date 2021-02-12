package com.qa.tdl.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.tdl.persistence.domain.ToDoList;

@Repository
public interface ToDoListRepo extends JpaRepository <ToDoList, Long>{

	@Query
	List<ToDoList> findByGroupName(String groupName);
}
