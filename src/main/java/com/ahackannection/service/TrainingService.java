package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Training;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface TrainingService {

	 Training saveTraining(Training training) throws ValidationException;
	 
	 Training findTrainingById(Long id);
	    
	 void removeTraining(Long trainingId) throws ValidationException;
		
	 List<Training> findTrainingsByTrainerId(Long trainerId);
		
	 List<Training> findTrainingsBySpecialization(String specialization);
	 
	 List<Training> findFreeTrainings();
	 
	 List<Training> findTrainingsByRating(int minRating);
	 
	 List<Training> findTrainingsByLanguage(String language);
		
     List<Training> findAllTrainings();
}
