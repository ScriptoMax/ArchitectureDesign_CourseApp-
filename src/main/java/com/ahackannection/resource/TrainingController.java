package com.ahackannection.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.Training;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.ContestService;
import com.ahackannection.service.TrainingService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/training")
@AllArgsConstructor
public class TrainingController {
	
	private final TrainingService trainingService;
	
	@PostMapping("/saveTraining")
	@ResponseBody
	public Training saveTraining(@RequestBody Training training) throws ValidationException {
		return trainingService.saveTraining(training);
	}
		
	@GetMapping("/findAllTrainings")
	public List<Training> findAllTrainings() {
		return trainingService.findAllTrainings();
	}
	
	@GetMapping("/findByTrainer")
	public List<Training> findTrainingsByOrganiserId(@RequestParam Long trainerId) {
		return trainingService.findTrainingsByTrainerId(trainerId);
	}
	
	@GetMapping("/findByLanguage")
	public List<Training> findTrainingsByLanguage(@RequestParam String language) {
		return trainingService.findTrainingsByLanguage(language);
	}
	
	@GetMapping("/findByRating")
	public List<Training> findByRating(@RequestParam int minRating) {
		return trainingService.findTrainingsByRating(minRating);
	}
	
	@GetMapping("/findTrainingById")
	public Training findTrainingById(@RequestParam Long id) {
		return trainingService.findTrainingById(id);
	}
	
	@GetMapping("/findBySpecialization")
	public List<Training> findBySpecialization(@RequestParam String spec) {
		return trainingService.findTrainingsBySpecialization(spec);
	}
		
	@GetMapping("/findFree")
	public List<Training> findFree() {
		return trainingService.findFreeTrainings();
	}
	
	@DeleteMapping("/removeTraining/{trainingId}")
	public ResponseEntity<Void> removeTraining(@PathVariable Long trainingId) throws ValidationException {
		trainingService.removeTraining(trainingId);
		return ResponseEntity.ok().build();
	}

}
