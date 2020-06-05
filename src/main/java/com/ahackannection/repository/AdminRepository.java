package com.ahackannection.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.user.Administrator;

@Repository
public interface AdminRepository extends CrudRepository<Administrator, Long>  {
	
}
