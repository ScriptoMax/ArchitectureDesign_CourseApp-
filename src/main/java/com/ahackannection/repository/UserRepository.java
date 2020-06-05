package com.ahackannection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
	
	User findUserByLogin(String login);
	
	User findUserByEmail(String email);	
	
}