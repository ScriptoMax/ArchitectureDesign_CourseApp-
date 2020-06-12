package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.user.Trainer;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.TrainerService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class TrainerTest {

	@Autowired
	private TrainerService trainerService; 
	
	@Test
	void testSaveTrainer() {
		Trainer newTrainer = new Trainer();
		Trainer newTrainerRet = new Trainer(); 
		newTrainer.setFirstName("Mykola");
		newTrainer.setSecondName("Borisovich");
		newTrainer.setLastName("Goncharuk");
		newTrainer.setLogin("MykolaStar3000");
		newTrainer.setPassword("default3000");
		newTrainer.setNumOfTrainings(0);
		newTrainer.setTeachExperience(3);
		newTrainer.setCountry("Spain");
		newTrainer.setSpecializations("Cryptofarming");
		newTrainer.setEmail("MykolaMail@gmail.com");
		newTrainer.setCorporate(false);
		newTrainer.setUserStatus("Active");
		newTrainer.setLanguages("Russian");
		newTrainer.setAffiliation("none");
		newTrainer.setAccount("23563881255");
		newTrainer.setAffiliated_status("none");
		newTrainer.setAverageRate(0);
		newTrainer.setWebsite("none");
		
		try {
			newTrainerRet = trainerService.saveTrainer(newTrainer);
			assertThat(newTrainerRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testTrainerById() {		
		Trainer trainer = trainerService.findTrainerById(47L);
		assertThat(trainer).isNotNull();
		assertEquals(trainer.getSpecializations(), "Biomolecular engineering");
	}
		
	@Test
	void testBlockTrainer() {
		Trainer trainer = trainerService.findTrainerById(51L);
		Trainer trainer1 = new Trainer();
		assertThat(trainer).isNotNull();
		assertEquals(trainer.getUserStatus(), "Active");
		trainer.setUserStatus("Blocked");
		try {
			trainer1 = trainerService.saveTrainer(trainer);
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		assertThat(trainer1).isNotNull();
		assertEquals(trainer1.getUserStatus(), "Blocked");	
	}
	
	@Test
	void testFindTrainersBySpecialization() {
		List<Trainer> trainers = trainerService.findTrainersBySpecialization("Genetics");
		Trainer trainer = trainerService.findTrainerById(36L);
		assertThat(trainers).isNotNull().isNotEmpty();	
		assertThat(trainers.contains(trainer));
	}
	
	@Test
	void testFindTrainersByLanguage() {
		List<Trainer> trainers = trainerService.findTrainersByLanguage("Spanish");
		Trainer trainer = trainerService.findTrainerById(47L);
		assertThat(trainers).isNotNull().isNotEmpty();	
		assertThat(trainers.contains(trainer));
	}
	
	@Test
	void testFindTrainersByRating() {
		List<Trainer> trainers = trainerService.findTrainersByRating(3.2f);
		Trainer trainer = trainerService.findTrainerById(7L);
		assertThat(trainers).isNotNull().isNotEmpty();	
		assertThat(trainers.contains(trainer));
	}	
	
	@Test
	void testFindAllTrainers() {
		List<Trainer> trainers = trainerService.findAllTrainers();
		assertThat(trainers).isNotNull().isNotEmpty();		
	}

}
