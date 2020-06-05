package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Contest;
import com.ahackannection.entity.User;
import com.ahackannection.entity.user.Visitor;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.VisitorRepository;
import com.ahackannection.service.VisitorService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VisitorServiceImpl implements VisitorService {
	
	@Autowired
	private final VisitorRepository visitorRepository;
	
	private void validateVisitor(Visitor visitor) throws ValidationException {
		if(visitor == null) {
			throw new ValidationException("Object created is null");
		}		
	} 
	
	public Visitor saveVisitor(Visitor visitor) throws ValidationException {
		validateVisitor(visitor);
		Visitor validVisitor = visitorRepository.save(visitor);
		return validVisitor;		
	}	
	
	
	public List<Visitor> findVisitorsByCity(String city) {
		
		List<Visitor> visitorList = Lists.newArrayList(visitorRepository.findAll());
		
		return visitorList.stream()
				.filter(x -> x.getCity().equals(city))
				//.map(userConverter::cast2UserDTO)
				.collect(Collectors.toList());
	}
	
    public Visitor findVisitorById(Long id) {
		
		Visitor matchVisitor = visitorRepository.findById(id).get();
		
		return matchVisitor;
	}	
	
	public List<Visitor> findAllVisitors() {
		
		List<Visitor> visitorList = Lists.newArrayList(visitorRepository.findAll());
		
		return visitorList.stream()				
				.collect(Collectors.toList());
	}	
}
