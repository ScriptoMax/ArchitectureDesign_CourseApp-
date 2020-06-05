package com.ahackannection.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.user.Organiser;

@Repository
public interface OrganiserRepository extends CrudRepository<Organiser, Long>  {	
	
	
}
