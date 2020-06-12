package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;
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
import com.ahackannection.entity.Contest;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.ContestService;
import com.ahackannection.service.OrganiserService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class ConTest {
	
	@Autowired
	private ContestService contestService; 
	
	@Autowired
	private OrganiserService organiserService;
	
	@Test
	void testSaveContest() {
		Contest newContest = new Contest();
		Contest newContestRet = new Contest(); 
		newContest.setTitle("Genetics Byte Battle 2020");
		newContest.setSpecialization("Software engineering in Genetics ");
		newContest.setOrganiser(organiserService.findOrganiserById(50L));
		newContest.setAnnotation("have you read title? What a problem?");
		newContest.setQuota(300);
		newContest.setNumOfSubmissions(0);
		newContest.setEventPlace("Malaga");
		newContest.setTraineeLevel((byte) 4);
		newContest.setPrerequisites("none");
		newContest.setRegistrationOpen(true);		
		
		try {
			newContestRet = contestService.saveContest(newContest);
			assertThat(newContestRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testFindContestById() {		
		Contest contest = contestService.findContestById(7L);
		assertThat(contest).isNotNull();
		assertEquals(contest.getEventPlace(), "Verona");
	}
		
	@Test
	void testChangeQuota() {
		Contest contest = contestService.findContestById(52L);
		Contest contest1 = new Contest();
		assertThat(contest).isNotNull();
		assertEquals(contest.getTitle(), "Bronx Battle");
		contest.setQuota(200);
		try {
			contest1 = contestService.saveContest(contest);
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		assertThat(contest1).isNotNull();
		assertEquals(contest1.getQuota(), 200);	
	}
	
	@Test
	void testFindContestsBySpecialization() {
		List<Contest> contests = contestService.findContestsBySpecialization("Applied Cybernetics");
		Contest contest = contestService.findContestById(6L);
		assertThat(contests).isNotNull().isNotEmpty();	
		assertThat(contests.contains(contest));
	}
	
	@Test
	void testFindcontestsByOrganiserId() {
		List<Contest> contests = contestService.findContestsByOrganiserId(50L);
		Contest contest = contestService.findContestById(18L);
		assertThat(contests).isNotNull().isNotEmpty();	
		assertThat(contests.contains(contest));
	}
	
	@Test
	void testFindContestWithRegOpen() {
		List<Contest> contests = contestService.findContestsWithRegOpen();
		Contest contest = contestService.findContestById(20L);
		assertThat(contests).isNotNull().isNotEmpty();	
		assertThat(contests.contains(contest));		
	}
	
	@Test
	void testFindAllContests() {
		List<Contest> contests = contestService.findAllContests();
		assertThat(contests).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testRemoveContest() {	
		assertThrows(NoSuchElementException.class, 
		() -> {
			contestService.removeContest(43L);
			Contest contest;
			contest = contestService.findContestById(43L);			
		});
	}


}
