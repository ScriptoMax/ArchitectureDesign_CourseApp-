package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.user.Trainer;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface TrainerService {

	Trainer saveTrainer(Trainer trainer) throws ValidationException;
	 
	List<Trainer> findTrainersByRating(float averageRate);
	 
	List<Trainer> findTrainersByJobExperience(float teachExperience);
		
	List<Trainer> findTrainersBySpecialization(String specializaion);
	
	List<Trainer> findTrainersByLanguage(String language);	 
	
	Trainer findTrainerById(Long id);	 
			
    List<Trainer> findAllTrainers();
}
