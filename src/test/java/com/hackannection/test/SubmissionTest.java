package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.Submission;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.ContestService;
import com.ahackannection.service.SubmissionService;
import com.ahackannection.service.VisitorService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class SubmissionTest {

	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private ContestService contestService;
	
	
	@Test
	void testSaveSubmission() {
		Submission newSubmission = new Submission();
		Submission newSubmissionRet = new Submission(); 
		newSubmission.setStatus("Accepted");
		newSubmission.setVisitor(visitorService.findVisitorById(52L));
		newSubmission.setContest(contestService.findContestById(16L));		
		
		try {
			newSubmissionRet = submissionService.saveSubmission(newSubmission);
			assertThat(newSubmissionRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testFindSubmissionById() {		
		Submission submission = submissionService.findSubmissionById(47L);
		assertThat(submission).isNotNull();
		assertEquals(submission.getVisitor().getLogin(), "delsol");
	}
		
	@Test
	void testAcceptSubmission() {
		Submission submission = submissionService.findSubmissionById(48L);
		Submission submission1 = new Submission();
		assertThat(submission).isNotNull();
		assertEquals(submission.getStatus(), "Received");
		submission.setStatus("Accepted");
		try {
			submission1 = submissionService.saveSubmission(submission);
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		assertThat(submission1).isNotNull();
		assertEquals(submission1.getStatus(), "Accepted");	
	}		
	
	@Test
	void testFindAcceptedSubmissions() {
		List<Submission> submissions = submissionService.findAllSubmissions();
		assertThat(submissions).isNotNull().isNotEmpty();	
		
		List<Submission> accceptedList = submissions.stream()
				.filter(x -> x.getStatus().equals("Accepted"))				
				.collect(Collectors.toList());
		
		assertThat(accceptedList).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testFindSubmissionsByVisitorId() {
		List<Submission> submissions = submissionService.findSubmissionsByUserId(35L);
		Submission submission = submissionService.findSubmissionById(47L);
		assertThat(submissions).isNotNull().isNotEmpty();	
		assertThat(submissions.contains(submission));
	}
	
	@Test
	void testFindSubmissionsByContestId() {
		List<Submission> submissions = submissionService.findSubmissionsByContestId(16L);
		Submission submission = submissionService.findSubmissionById(48L);
		assertThat(submissions).isNotNull().isNotEmpty();	
		assertThat(submissions.contains(submission));
	}	
	
	
	@Test
	void testFindAllSubmissions() {
		List<Submission> submissions = submissionService.findAllSubmissions();
		assertThat(submissions).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testRemoveSubmission() {	
		assertThrows(NoSuchElementException.class, 
		() -> {
			submissionService.removeSubmission(43L);
			Submission submission;
			submission = submissionService.findSubmissionById(43L);			
		});
	}
	
}
