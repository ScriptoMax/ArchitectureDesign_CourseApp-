package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.user.Administrator;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.AdminRepository;
import com.ahackannection.service.AdministratorService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private final AdminRepository administratorRepository;
	
	private void validateAdministrator(Administrator administrator) throws ValidationException {
		if(administrator == null) {
			throw new ValidationException("Object created is null");
		}		
	}
	
	public Administrator saveAdministrator(Administrator administrator) throws ValidationException {
		validateAdministrator(administrator);
		Administrator validAdministrator = administratorRepository.save(administrator);
		return validAdministrator;		
	}			
	
	public List<Administrator> findAllAdministrators() {
		
		List<Administrator> administratorList = Lists.newArrayList(administratorRepository.findAll());
		
		return administratorList.stream()				
				.collect(Collectors.toList());
	}	
}
