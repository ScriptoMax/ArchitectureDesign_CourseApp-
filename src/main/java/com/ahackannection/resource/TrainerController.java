package com.ahackannection.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.User;
import com.ahackannection.entity.user.Trainer;
import com.ahackannection.entity.user.Visitor;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.TrainerService;
import com.ahackannection.service.VisitorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/trainer")
@AllArgsConstructor
public class TrainerController {
	
	private final TrainerService trainerService;
	
	@PostMapping("/saveTrainer")
	@ResponseBody
	public Trainer saveTrainer(@RequestBody Trainer trainer) throws ValidationException {
		return trainerService.saveTrainer(trainer);
	}
	
	@GetMapping("/findTrainersByRating")
	public List<Trainer> findTrainersByRating(@RequestParam float averageRate) {
		return trainerService.findTrainersByRating(averageRate);
	}
	
	@GetMapping("/findTrainersByExperience")
	public List<Trainer> findTrainersByJobExperience(@RequestParam float teachExperience) {
		return trainerService.findTrainersByJobExperience(teachExperience);
	}
	
	@GetMapping("/findTrainersBySpecialization")
	public List<Trainer> findTrainersBySpecialization(@RequestParam String specialization) {
		return trainerService.findTrainersBySpecialization(specialization);
	}
	
	@GetMapping("/findTrainerById")
	public Trainer findTrainerById(@RequestParam Long id) {
		return trainerService.findTrainerById(id);
	}
	
	@GetMapping("/findTrainersByLanguage")
	public List<Trainer> findTrainersByLanguage(@RequestParam String language) {
		return trainerService.findTrainersByLanguage(language);
	}
	
	@GetMapping("/findAllTrainers")
	public List<Trainer> findAllTrainers() {
		return trainerService.findAllTrainers();		
	}
}
