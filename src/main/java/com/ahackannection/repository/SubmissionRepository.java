package com.ahackannection.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.Submission;
import com.ahackannection.exception.ValidationException;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Long>  {       
	
	List<Submission> findSubmissionsByVisitor(Long userId);
	
	List<Submission> findSubmissionsByContest(Long contestId);		
	
}
