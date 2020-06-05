package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Submission;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface SubmissionService {
	
    Submission saveSubmission(Submission submission) throws ValidationException;
    
    Submission findSubmissionById(Long submissionId); 
    
    void removeSubmission(Long submissionId) throws ValidationException;
	
    void acceptSubmission(Long submissionId);   
	
	List<Submission> findSubmissionsByUserId(Long userId);
	
	List<Submission> findSubmissionsByContestId(Long contestId);
	
	List<Submission> findAllSubmissions();	

}
