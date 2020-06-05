package com.ahackannection.resource;

import java.util.List;
import java.util.Set;

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

import com.ahackannection.entity.Contest;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.ContestService;

import java.sql.Date;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/contest")
@AllArgsConstructor
public class ContestController {

	
	private final ContestService contestService;	
	
	@PostMapping("/saveContest")
	@ResponseBody
	public Contest saveContest(@RequestBody Contest contest) throws ValidationException {
		return contestService.saveContest(contest);
	}
		
	@PutMapping("/changeQuota")
	public void changeQuota(@RequestParam Long contestId, int newQuota) {
		contestService.changeQuota(contestId, newQuota);
	}
	
	@GetMapping("/findSpecializations")
	public Set<String> findContestSpecializations() {
		return contestService.findContestSpecializations();
	}
	
	@GetMapping("/findContestById")
	public Contest findContestById(@RequestParam Long id) {
		return contestService.findContestById(id);
	}
	
	@GetMapping("/findBySpecialization")
	public List<Contest> findContestsBySpecialization(@RequestParam String contestSpec) {
		return contestService.findContestsBySpecialization(contestSpec);
	}
	
	@GetMapping("/findAllContests")
	public List<Contest> findAllContests() {
		return contestService.findAllContests();
	}
	
	@GetMapping("/findByOrganiser")
	public List<Contest> findContestsByOrganiserId(@RequestParam Long organiserId) {
		return contestService.findContestsByOrganiserId(organiserId);
	}
	
	@GetMapping("/findWithinInterval")
	public List<Contest> findContestsWithinInterval(@RequestParam Date start, Date runoff) {
		return contestService.findContestsWithinInterval(start, runoff);
	}
	
	@GetMapping("/findByComplexity")
	public List<Contest> findByContest(@RequestParam byte complexLevel) {
		return contestService.findContestsByComplexity(complexLevel);
	}
	
	@DeleteMapping("/removeContest/{contestId}")
	public ResponseEntity<Void> removeContest(@PathVariable Long contestId) throws ValidationException {
		contestService.removeContest(contestId);
		return ResponseEntity.ok().build();
	}
}
