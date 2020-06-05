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

import com.ahackannection.entity.Submission;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.SubmissionService;
import com.ahackannection.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/submission")
@AllArgsConstructor
public class SubmissionController {

	private final SubmissionService submissionService;	
	
	@PostMapping("/saveSubmission")
	@ResponseBody
	public Submission saveSubmission(@RequestBody Submission submission) throws ValidationException {
		return submissionService.saveSubmission(submission);
	}
		
	@PutMapping("/acceptSubmission")
	public void acceptSubmission(@RequestParam Long submissionId) {
		submissionService.acceptSubmission(submissionId);
	}
	
	@GetMapping("/findSubmissionById")
	public Submission findSubmissionById(@RequestParam Long id) {
		return submissionService.findSubmissionById(id);
	}
	
	@GetMapping("/findAllSubmissions")
	public List<Submission> findAllSubmissions() {
		return submissionService.findAllSubmissions();
	}
	
	@GetMapping("/findByVisitor")
	public List<Submission> findSubmissionsByUserId(@RequestParam Long userId) {
		return submissionService.findSubmissionsByUserId(userId);
	}
	
	@GetMapping("/findByContest")
	public List<Submission> findByContest(@RequestParam Long contestId) {
		return submissionService.findSubmissionsByContestId(contestId);
	}
	
	@DeleteMapping("/removeSubmission/{submissionId}")
	public ResponseEntity<Void> removeSubmission(@PathVariable Long submissionId) throws ValidationException {
		submissionService.removeSubmission(submissionId);
		return ResponseEntity.ok().build();
	}
	
}
