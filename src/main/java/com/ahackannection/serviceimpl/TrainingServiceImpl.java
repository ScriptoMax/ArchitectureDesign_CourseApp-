package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Training;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.TrainingRepository;
import com.ahackannection.service.TrainingService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private final TrainingRepository trainingRepository;	
	
	private void validateTraining(Training training) throws ValidationException {
		if(training == null) {
			throw new ValidationException("Object created is null");
		}		
	}
	
	@Override
	public Training saveTraining(Training training) throws ValidationException {
		validateTraining(training);
		Training validTraining = trainingRepository.save(training);
		return validTraining;		
	}
		
	@Override
	public void removeTraining(Long trainingId) {
		trainingRepository.deleteById(trainingId);
	}
	
	@Override
	public List<Training> findTrainingsByTrainerId(Long trainerId) {
		List<Training> trainingList = Lists.newArrayList(trainingRepository.findAll());
		
		return trainingList.stream()
				.filter(x -> x.getTrainer().getId() == trainerId)				
				.collect(Collectors.toList());	
	}
	
	@Override
	public Training findTrainingById(Long id) {
		Training training = trainingRepository.findById(id).get();
		
		return training;
	}
	
	@Override
	public List<Training> findTrainingsBySpecialization(String spec) {
		List<Training> trainingList = Lists.newArrayList(trainingRepository.findAll());
		
		return trainingList.stream()
				.filter(x -> x.getSpecialization().equals(spec))				
				.collect(Collectors.toList());			
	}
	
	@Override
	public List<Training> findTrainingsByRating(int minRating) {
		List<Training> trainingList = Lists.newArrayList(trainingRepository.findAll());
		
		return trainingList.stream()
				.filter(x -> x.getAvgRate() >= minRating)				
				.collect(Collectors.toList());			
	}
	
	@Override
	public List<Training> findFreeTrainings() {
		List<Training> trainingList = Lists.newArrayList(trainingRepository.findAll());
		
		return trainingList.stream()
				.filter(x -> x.isFree())				
				.collect(Collectors.toList());			
	}
	
	@Override
	public List<Training> findTrainingsByLanguage(String language) {
		List<Training> trainingList = Lists.newArrayList(trainingRepository.findAll());
		
		return trainingList.stream()
				.filter(x -> x.getLanguage().equals(language))				
				.collect(Collectors.toList());			
	}
	
	@Override
	public List<Training> findAllTrainings() {
		
		List<Training> trainingList = Lists.newArrayList(trainingRepository.findAll());
		
		return trainingList.stream()				
				.collect(Collectors.toList());
	}
	
}
