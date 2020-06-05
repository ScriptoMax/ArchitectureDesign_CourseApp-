package com.ahackannection.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.user.Trainer;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long>  {
	
	List<Trainer> findTrainersBySpecializations(String specializaion);
	 
	List<Trainer> findTrainersByLanguages(String language);	 
	
}