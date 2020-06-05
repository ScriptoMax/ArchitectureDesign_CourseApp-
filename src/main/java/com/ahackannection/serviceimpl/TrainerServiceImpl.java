package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.User;
import com.ahackannection.entity.user.Trainer;
import com.ahackannection.entity.user.Visitor;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.SubmissionRepository;
import com.ahackannection.repository.TrainerRepository;
import com.ahackannection.service.TrainerService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrainerServiceImpl implements TrainerService {
	
	@Autowired
	private final TrainerRepository trainerRepository;
	
	private void validateTrainer(Trainer trainer) throws ValidationException {
		if(trainer == null) {
			throw new ValidationException("Object created is null");
		}		
	}
	
	public Trainer saveTrainer(Trainer trainer) throws ValidationException {
		validateTrainer(trainer);
		Trainer validTrainer = trainerRepository.save(trainer);
		return validTrainer;		
	}		
	
	public List<Trainer> findTrainersByRating(float averageRate) {
		
		List<Trainer> trainerList = Lists.newArrayList(trainerRepository.findAll());
		
		return trainerList.stream()
				.filter(x -> x.getAverageRate() >= averageRate)				
				.collect(Collectors.toList());
	}	
	
	public List<Trainer> findTrainersByJobExperience(float teachExperience) {
		
		List<Trainer> trainerList = Lists.newArrayList(trainerRepository.findAll());
		
		return trainerList.stream()
				.filter(x -> x.getTeachExperience() >= teachExperience)				
				.collect(Collectors.toList());
	}	
	
	public Trainer findTrainerById(Long id) {
		
		Trainer trainer = trainerRepository.findById(id).get();
		
		return trainer;
	}	
	
	public List<Trainer> findTrainersBySpecialization(String specialization) {
		
		List<Trainer> trainerList = Lists.newArrayList(trainerRepository.findAll());
		
		return trainerList.stream()
				.filter(x -> x.getSpecializations().equals(specialization))				
				.collect(Collectors.toList());
	}	
	
	
	public List<Trainer> findTrainersByLanguage(String language) {
		
		List<Trainer> trainerList = Lists.newArrayList(trainerRepository.findAll());
		
		return trainerList.stream()
				.filter(x -> x.getLanguages().equals(language))				
				.collect(Collectors.toList());
	}	
	
	
	public List<Trainer> findAllTrainers() {
		
		List<Trainer> trainerList = Lists.newArrayList(trainerRepository.findAll());
		
		return trainerList.stream()				
				.collect(Collectors.toList());
	}	
}
