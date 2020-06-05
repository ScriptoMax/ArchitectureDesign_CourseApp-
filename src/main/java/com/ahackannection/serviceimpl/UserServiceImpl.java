package com.ahackannection.serviceimpl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.security.auth.x500.X500Principal;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.User;
import com.ahackannection.entity.user.Administrator;
import com.ahackannection.entity.user.Organiser;
import com.ahackannection.entity.user.Trainer;
import com.ahackannection.entity.user.Visitor;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.AdminRepository;
import com.ahackannection.repository.OrganiserRepository;
import com.ahackannection.repository.TrainerRepository;
import com.ahackannection.repository.UserRepository;
import com.ahackannection.repository.VisitorRepository;
import com.ahackannection.service.AdministratorService;
import com.ahackannection.service.OrganiserService;
import com.ahackannection.service.TrainerService;
import com.ahackannection.service.UserService;
import com.ahackannection.service.VisitorService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final VisitorService visitorService;
	
	@Autowired
	private final AdministratorService adminService;
	
	@Autowired
	private final OrganiserService organiserService;
	
	@Autowired
	private final TrainerService trainerService;
	
	
	private void validateUser(User user) throws ValidationException {
		if(user == null) {
			throw new ValidationException("Object created is null");
		}
		
		if(user.getLogin() == null) {
			throw new ValidationException("User name field is empty");
		}
	}	
	
	@Override
	public <T extends User> Long saveUser(T obj) throws ValidationException {
		
		Long userId;
		String role = obj.getRole();
		
		if(role.equals("VISITOR")) {
			Visitor visitor = (Visitor) obj;
			validateUser(visitor);
			userId = visitorService.saveVisitor(visitor).getId();			 
		} else if(role.equals("ADMINISTRATOR")) {
			Administrator administrator = (Administrator) obj;
			validateUser(administrator);
			userId = adminService.saveAdministrator(administrator).getId();
		} else if(role.equals("ORGANISER")) {
			Organiser organiser = (Organiser) obj;
			validateUser(organiser);
			userId = organiserService.saveOrganiser(organiser).getId();
		} else {
			Trainer trainer = (Trainer) obj;
			validateUser(trainer);
			userId = trainerService.saveTrainer(trainer).getId();
		}	
		
		return userId;
	}
	
	@Override
	public void removeUser(Long userId) {
		userRepository.deleteById(userId);
	}
	
	@Override
	public void blockUser(Long userId) {
		User matchUser = userRepository.findById(userId).get();  
		matchUser.setUserStatus("BLOCKED");
		userRepository.save(matchUser);
	}
	
	@Override
	public User findUserByLogin(String login) {
		User matchUser = userRepository.findUserByLogin(login);
		return matchUser;		
	}
	
	@Override
	public User findUserById(Long id) {
		User matchUser = userRepository.findById(id).get();
		return matchUser;		
	}
	
	@Override
	public User findUserByEmail(String email) {
		User matchUser = userRepository.findUserByEmail(email);		
		return matchUser;		
	}
	
	
	@Override
	public List<User> findUsersByCountry(String country) {
		
		List<User> userList = Lists.newArrayList(userRepository.findAll());
		
		return userList.stream()
				.filter(x -> x.getCountry().equals(country))				
				.collect(Collectors.toList());
	}
	
	@Override
	public List<User> findUsersByCorporate() {
		
		List<User> userList = Lists.newArrayList(userRepository.findAll());
		
		return userList.stream()
				.filter(x -> x.isCorporate())
				.collect(Collectors.toList());
	}
	
	@Override
	public List<User> findAllUsers() {
		
		List<User> userList = Lists.newArrayList(userRepository.findAll());
		
		return userList.stream()
				.collect(Collectors.toList());
	}
}
