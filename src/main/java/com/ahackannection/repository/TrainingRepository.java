package com.ahackannection.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.Training;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Long>  {
	
	List<Training> findTrainingsByTrainer(Long trainerId);
	
	List<Training> findTrainingsBySpecialization(String specialization);	
	 
	List<Training> findTrainingsByAvgRate(int avgRate);
	
	List<Training> findTrainingsByLanguage(String language);	 
	
}
