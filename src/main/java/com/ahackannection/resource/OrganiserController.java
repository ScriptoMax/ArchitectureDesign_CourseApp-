package com.ahackannection.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.user.Organiser;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.OrganiserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/organiser")
@AllArgsConstructor
public class OrganiserController {
	
	private final OrganiserService organiserService;
	
	@PostMapping("/saveOrganiser")
	@ResponseBody
	public Organiser saveOrganiser(@RequestBody Organiser organiser) throws ValidationException {
		return organiserService.saveOrganiser(organiser);
	}
	
	@GetMapping("/findAllOrganisers")
	public List<Organiser> findAllOrganisers() {
		return organiserService.findAllOrganisers();		
	}
}
