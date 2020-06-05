package com.ahackannection.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.TrainingReview;

@Repository
public interface ReviewRepository extends CrudRepository<TrainingReview, Long>  {
	
}