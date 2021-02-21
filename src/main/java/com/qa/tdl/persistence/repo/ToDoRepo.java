package com.qa.tdl.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.tdl.persistence.domain.ToDo;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long>{
	
	@Query
	List<ToDo> findByTask(String task);

}
