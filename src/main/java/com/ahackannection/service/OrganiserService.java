package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.user.Organiser;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface OrganiserService {
	
	Organiser saveOrganiser(Organiser organiser) throws ValidationException;
	
	List<Organiser> findAllOrganisers();

}
