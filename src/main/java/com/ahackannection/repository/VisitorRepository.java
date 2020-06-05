package com.ahackannection.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.User;
import com.ahackannection.entity.user.Visitor;
import com.ahackannection.exception.ValidationException;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Long>  {
	
	List<Visitor> findVisitorsByCity(String city);	
	
}