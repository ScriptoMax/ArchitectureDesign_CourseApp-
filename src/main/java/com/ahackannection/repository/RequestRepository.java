package com.ahackannection.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.SupportRequest;

@Repository
public interface RequestRepository extends CrudRepository<SupportRequest, Long>  {
	
	SupportRequest findRequestById(Long requestId);
}
