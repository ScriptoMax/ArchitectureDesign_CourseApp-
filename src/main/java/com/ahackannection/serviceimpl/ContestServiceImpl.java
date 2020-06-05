package com.ahackannection.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.security.auth.x500.X500Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Contest;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.ContestRepository;
import com.ahackannection.service.ContestService;
import com.google.common.collect.Lists;

import java.sql.Date;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContestServiceImpl implements ContestService {
	
	@Autowired
	private final ContestRepository contestRepository;	
	
	private void validateContest(Contest contest) throws ValidationException {
		if(contest == null) {
			throw new ValidationException("Object created is null");
		}		
	} 	
	
	public Contest saveContest(Contest contest) throws ValidationException {
		validateContest(contest);
		Contest validContest = contestRepository.save(contest);
		return validContest;	
	}
		
	
	public void removeContest(Long contestId) {
		contestRepository.deleteById(contestId);
	}
	
	public Contest findContestById(Long contestId) {		
		Contest contest = contestRepository.findById(contestId).get();
		return contest;	
	}
	
	
	public void changeQuota(Long contestId, int newQuota) {
		Contest matchContest = contestRepository.findById(contestId).get();
		matchContest.setQuota(newQuota);
		contestRepository.save(matchContest);		
	}
	
	public Set<String> findContestSpecializations() {
		List<Contest> contestList = Lists.newArrayList(contestRepository.findAll());
		Set<String> specializations = new TreeSet<String>(); 
		
		for (Contest contest : contestList)
			specializations.add(contest.getSpecialization());
		
		return specializations;
	}
	
	public List<Contest> findContestsBySpecialization(String contestSpec) {
		List<Contest> contestList = Lists.newArrayList(contestRepository.findAll());
		
		return contestList.stream()
				.filter(x -> x.getSpecialization().equals(contestSpec))				
				.collect(Collectors.toList());	
	}
	
	public List<Contest> findContestsByOrganiserId(Long organiserId) {
		List<Contest> contestList = Lists.newArrayList(contestRepository.findAll());
		
		return contestList.stream()
				.filter(x -> x.getOrganiser().getId() == organiserId)				
				.collect(Collectors.toList());	
	}
	
	public List<Contest> findContestsWithinInterval(Date start, Date runoff) {		
		List<Contest> contestList = Lists.newArrayList(contestRepository.findAll());
		
		return contestList.stream()
				.filter(x -> x.getEventStart().after(start) && x.getEventFinish().before(runoff))
				.collect(Collectors.toList());		
	}
	
	
	public List<Contest> findContestsByComplexity(byte traineeLevel) {
		List<Contest> contestList = Lists.newArrayList(contestRepository.findAll());
		
		return contestList.stream()
				.filter(x -> x.getTraineeLevel() == traineeLevel)				
				.collect(Collectors.toList());			
	}
	
	
	public List<Contest> findContestsWithRegOpen() {
		List<Contest> contestList = Lists.newArrayList(contestRepository.findAll());
		
		return contestList.stream()
				.filter(x -> x.isRegistrationOpen())				
				.collect(Collectors.toList());			
	}		
	
	public List<Contest> findAllContests() {
		
		List<Contest> contestList = Lists.newArrayList(contestRepository.findAll());
		
		return contestList.stream()				
				.collect(Collectors.toList());
	}

}
