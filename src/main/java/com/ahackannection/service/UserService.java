package com.ahackannection.service;

import java.util.List;

import org.hibernate.type.TrueFalseType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.User;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface UserService {
	
	<T extends User> Long saveUser(T obj) throws ValidationException;
	
	void removeUser(Long userId);
	
	void blockUser(Long userId);
	
	User findUserById(Long id);
	
	User findUserByLogin(String login);
	
	User findUserByEmail(String email);
	
	List<User> findUsersByCountry(String country);
	
	List<User> findUsersByCorporate();
	
	List<User> findAllUsers();
	
}
