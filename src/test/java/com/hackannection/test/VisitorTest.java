package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.user.Visitor;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.VisitorService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
public class VisitorTest {
	
	@Autowired
	private VisitorService visitorService;	
	
	@Test
	void testSaveVisitor() {
		Visitor newVisitor = new Visitor();
		Visitor newVisitorRet = new Visitor(); 
		newVisitor.setFirstName("Joaquin");
		newVisitor.setSecondName("Esteban");
		newVisitor.setLastName("Ramires");
		newVisitor.setLogin("default3000");
		newVisitor.setPassword("default3000");
		newVisitor.setCity("Sevilla");
		newVisitor.setContestBackground("likely in the future");
		newVisitor.setCountry("Spain");
		newVisitor.setPortfolio("likely in the future");
		newVisitor.setEmail("pepperhot@gmail.com");
		newVisitor.setCorporate(false);
		newVisitor.setUserStatus("Active");
		
		try {
			newVisitorRet = visitorService.saveVisitor(newVisitor);
			System.out.println(newVisitorRet.getId());
			assertThat(newVisitor).isNotNull();
		} catch (ValidationException e) {			
			e.printStackTrace();
		} 
	}
	
	@Test
	void testVisitorById() {		
		Visitor visitor = visitorService.findVisitorById(48L);
		assertThat(visitor).isNotNull();
		assertEquals(visitor.getCity(), "Zagreb");
	}
		
	@Test
	void testBlockVisitor() {
		Visitor visitor = visitorService.findVisitorById(48L);
		Visitor visitor1 = new Visitor();
		assertThat(visitor).isNotNull();
		assertEquals(visitor.getUserStatus(), "Active");
		visitor.setUserStatus("Blocked");
		try {
			visitor1 = visitorService.saveVisitor(visitor);
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		assertThat(visitor1).isNotNull();
		assertEquals(visitor1.getUserStatus(), "Blocked");	
	}	
	
	@Test
	void testFindVisitorsByCity() {
		List<Visitor> visitors = visitorService.findVisitorsByCity("Montpellier");
		Visitor visitor = visitorService.findVisitorById(35L);
		assertThat(visitors).isNotNull().isNotEmpty();	
		assertThat(visitors.contains(visitor));
	}
	
	@Test
	void testFindAllVisitors() {
		List<Visitor> visitors = visitorService.findAllVisitors();
		assertThat(visitors).isNotNull().isNotEmpty();		
	}
	
}

