package com.ahackannection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.Contest;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long>  {
	
	 List<Contest> findContestsByOrganiserId(Long organiserId);
	 
	 List<Contest> findContestsBySpecialization(String contestSpec);
	 
	 Contest findContestById(Long contestId);
		
	 List<Contest> findContestsByTraineeLevel(byte traineeLevel);	 	
    
}