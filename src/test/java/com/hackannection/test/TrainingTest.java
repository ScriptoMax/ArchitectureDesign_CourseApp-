package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.Training;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.TrainerService;
import com.ahackannection.service.TrainingService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class TrainingTest {

	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private TrainerService trainerService;
	
	@Test
	void testSaveTraining() {
		Training newTraining = new Training();
		Training newTrainingRet = new Training(); 
		newTraining.setTitle("Essentials of encrypring algorithms and techniques. Part 1");
		newTraining.setSpecialization("Cryptography and secure messaging");
		newTraining.setTrainer(trainerService.findTrainerById(45L));
		newTraining.setAnnotation("same as title, sure");
		newTraining.setLanguage("English");
		newTraining.setAvgRate(0);
		newTraining.setTotalRates(0);
		newTraining.setTotalStudents(0);
		newTraining.setDuration("5:24");
		newTraining.setFree(false);
		newTraining.setPrice(100);
		newTraining.setComplexLevel((byte) 1);
		newTraining.setHasCertificateOption(true);
		
		
		try {
			newTrainingRet = trainingService.saveTraining(newTraining);
			assertThat(newTrainingRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testFindTrainingById() {		
		Training training = trainingService.findTrainingById(15L);
		assertThat(training).isNotNull();
		assertEquals(training.getSpecialization(), "Cryptography");
	}
		
	
	@Test
	void testFindTrainingsBySpecialization() {
		List<Training> trainings = trainingService.findTrainingsBySpecialization("Technical writing");
		Training training = trainingService.findTrainingById(16L);
		assertThat(trainings).isNotNull().isNotEmpty();	
		assertThat(trainings.contains(training));
	}
	
	@Test
	void testFindTrainingsByLanguage() {
		List<Training> trainings = trainingService.findTrainingsByLanguage("German");
		Training training = trainingService.findTrainingById(8L);
		assertThat(trainings).isNotNull().isNotEmpty();	
		assertThat(trainings.contains(training));
	}
	
	@Test
	void testFindTrainingsByRating() {
		List<Training> trainings = trainingService.findTrainingsByRating(4);
		assertThat(trainings).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testFindTrainingsByTrainerId() {
		List<Training> trainings = trainingService.findTrainingsByTrainerId(45L);
		Training training = trainingService.findTrainingById(14L);
		assertThat(trainings).isNotNull().isNotEmpty();	
		assertThat(trainings.contains(training));
	}
	
	@Test
	void testFindFreeTrainings() {
		List<Training> trainings = trainingService.findFreeTrainings();
		Training training = trainingService.findTrainingById(7L);
		assertThat(trainings).isNotNull().isNotEmpty();	
		assertThat(!(trainings.contains(training)));
	}
		
	@Test
	void testFindAllTrainings() {
		List<Training> Trainings = trainingService.findAllTrainings();
		assertThat(Trainings).isNotNull().isNotEmpty();		
	}

	@Test
	void testRemoveTraining() {	
		assertThrows(NoSuchElementException.class, 
		() -> {
			trainingService.removeTraining(43L);
			Training training;  
			training = trainingService.findTrainingById(43L);			
		});
	}

}
