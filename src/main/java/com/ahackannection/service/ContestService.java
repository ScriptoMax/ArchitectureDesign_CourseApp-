package com.ahackannection.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Contest;
import com.ahackannection.exception.ValidationException;

import java.sql.Date;

@Service
@Component
public interface ContestService {
	
	 Contest saveContest(Contest contestDTO) throws ValidationException;
	    
	 void removeContest(Long contestId) throws ValidationException;
		
	 void changeQuota(Long contestId, int newQuota);   
		
	 Contest findContestById(Long contestId);
	 
	 Set<String> findContestSpecializations();
	 
	 List<Contest> findContestsByOrganiserId(Long organiserId);
	 
	 List<Contest> findContestsBySpecialization(String contestSpec);
	 
	 List<Contest> findContestsWithinInterval(Date start, Date runoff);
		
	 List<Contest> findContestsByComplexity(byte traineeLevel);
	 
	 List<Contest> findContestsWithRegOpen();
		
     List<Contest> findAllContests();

}
