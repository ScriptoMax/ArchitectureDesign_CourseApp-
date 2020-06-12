package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.user.Administrator;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface AdministratorService {
	
	Administrator saveAdministrator(Administrator administrator) throws ValidationException;
	
	Administrator findAdminById(Long id);
	
	List<Administrator> findAllAdministrators();

}
