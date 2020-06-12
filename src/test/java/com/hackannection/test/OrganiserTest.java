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
import com.ahackannection.entity.user.Organiser;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.OrganiserService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class OrganiserTest {
	
	@Autowired
	private OrganiserService organiserService; 
	
	@Test
	void testSaveOrganiser() {
		Organiser newOrganiser = new Organiser();
		Organiser newOrganiserRet = new Organiser(); 
		newOrganiser.setFirstName("Jakob");
		newOrganiser.setLastName("Graham");
		newOrganiser.setLogin("descendant");
		newOrganiser.setPassword("default3000");
		newOrganiser.setCountry("Ireland");
		newOrganiser.setEmail("descendant@gmail.com");
		newOrganiser.setCorporate(true);
		newOrganiser.setUserStatus("Active");
		newOrganiser.setAffiliation("Cambridge University");
		newOrganiser.setAffiliated_status("Fellow associate");
		newOrganiser.setOpenContestNumber(0);
		newOrganiser.setTotalContestNumber(0);
		newOrganiser.setWebsite("none");
		
		try {
			newOrganiserRet = organiserService.saveOrganiser(newOrganiser);
			assertThat(newOrganiserRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testFindOrganiserById() {		
		Organiser organiser = organiserService.findOrganiserById(8L);
		assertThat(organiser).isNotNull();
		assertThat(organiser.getContests()).isNotNull().isNotEmpty();
	}
		
	@Test
	void testBlockOrganiser() {
		Organiser organiser = organiserService.findOrganiserById(50L);
		Organiser organiser1 = new Organiser();
		assertThat(organiser).isNotNull();
		assertEquals(organiser.getUserStatus(), "Active");
		organiser.setUserStatus("Blocked");
		try {
			organiser1 = organiserService.saveOrganiser(organiser);
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		assertThat(organiser1).isNotNull();
		assertEquals(organiser1.getUserStatus(), "Blocked");	
	}
	
	@Test
	void testFindAllOrganisers() {
		List<Organiser> Organisers = organiserService.findAllOrganisers();
		assertThat(Organisers).isNotNull().isNotEmpty();		
	}

}

