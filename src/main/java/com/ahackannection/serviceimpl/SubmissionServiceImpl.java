package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Submission;
import com.ahackannection.entity.User;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.SubmissionRepository;
import com.ahackannection.service.SubmissionService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
	
	@Autowired
	private final SubmissionRepository submissionRepository;
	
	private void validateSubmission(Submission submission) throws ValidationException {
		if(submission == null) {
			throw new ValidationException("Object created is null");
		}		
	}
	
	
	public Submission saveSubmission(Submission submission) throws ValidationException {
		validateSubmission(submission);
		Submission validSubmission = submissionRepository.save(submission);
		return validSubmission;	
	}
		
	
	public void removeSubmission(Long submissionId) {
		submissionRepository.deleteById(submissionId);
	}
	
	
	public void acceptSubmission(Long submissionId) {
		Submission matchSubmission = submissionRepository.findById(submissionId).get();  
		matchSubmission.setStatus("ACCEPTED");
		submissionRepository.save(matchSubmission);
	}
	
	
	public Submission findSubmissionById(Long submissionId) {
		Submission submission = submissionRepository.findById(submissionId).get();
		return submission;
	}
	
	
	@Override
	public List<Submission> findSubmissionsByUserId(Long userId) {
		List<Submission> submissionList = Lists.newArrayList(submissionRepository.findAll());
		
		return submissionList.stream()
				.filter(x -> x.getVisitor().getId() == userId)				
				.collect(Collectors.toList());	
	}
	
	
	@Override
	public List<Submission> findSubmissionsByContestId(Long contestId) {
		List<Submission> submissionList = Lists.newArrayList(submissionRepository.findAll());
		
		return submissionList.stream()
				.filter(x -> x.getContest().getId().equals(contestId))				
				.collect(Collectors.toList());			
	}
	
	@Override
	public List<Submission> findAllSubmissions() {
		
		List<Submission> submissionList = Lists.newArrayList(submissionRepository.findAll());
		
		return submissionList.stream()				
				.collect(Collectors.toList());
	}
}
