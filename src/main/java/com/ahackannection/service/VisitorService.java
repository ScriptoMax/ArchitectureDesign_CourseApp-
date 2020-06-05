package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.User;
import com.ahackannection.entity.user.Visitor;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface VisitorService {
	
	Visitor saveVisitor(Visitor visitor) throws ValidationException;	
	
	List<Visitor> findVisitorsByCity(String city);
	
	Visitor findVisitorById(Long id);
	
	List<Visitor> findAllVisitors();

}
