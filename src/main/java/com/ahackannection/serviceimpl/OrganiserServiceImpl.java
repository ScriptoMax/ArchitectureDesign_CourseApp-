package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.user.Organiser;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.OrganiserRepository;
import com.ahackannection.service.OrganiserService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrganiserServiceImpl implements OrganiserService {
	
	@Autowired
	private final OrganiserRepository organiserRepository;
	
	private void validateOrganiser(Organiser organiser) throws ValidationException {
		if(organiser == null) {
			throw new ValidationException("Object created is null");
		}		
	}
	
	public Organiser saveOrganiser(Organiser organiser) throws ValidationException {
		validateOrganiser(organiser);
		Organiser validOrganiser = organiserRepository.save(organiser);
		return validOrganiser;		
	}		
	
	public Organiser findOrganiserById(Long id) {
		
		Organiser organiser = organiserRepository.findById(id).get();
		
		return organiser;
	}
	
	public List<Organiser> findAllOrganisers() {
		
		List<Organiser> organiserList = Lists.newArrayList(organiserRepository.findAll());
		
		return organiserList.stream()				
				.collect(Collectors.toList());
	}	

}
