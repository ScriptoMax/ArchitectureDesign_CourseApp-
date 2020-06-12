package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.SupportRequest;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.AdministratorService;
import com.ahackannection.service.RequestService;
import com.ahackannection.service.UserService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class RequestTest {

	@Autowired
	private RequestService requestService;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Test
	void testSaveRequest() {
		SupportRequest newRequest = new SupportRequest();
		SupportRequest newRequestRet = new SupportRequest(); 
		newRequest.set_user(userService.findUserById(6L));
		newRequest.setAdmin(administratorService.findAdminById(31L));
	    newRequest.setRequestStatus("Received");
		newRequest.setTopic("Authentication failure incident");
		newRequest.setBody("too annoying to explain");
				
		try {
			newRequestRet = requestService.saveRequest(newRequest);
			assertThat(newRequestRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testFindRequestById() {		
		SupportRequest supportRequest = requestService.findRequestById(1L);
		assertThat(supportRequest).isNotNull();
		assertEquals(supportRequest.getAdmin().getLogin(), "alone");
	}
	
	@Test
	void testFindAllRequests() {
		List<SupportRequest> requests = requestService.findAllRequests();
		assertThat(requests).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testResolveRequest() {
		SupportRequest request = requestService.findRequestById(1L);
		SupportRequest request1 = new SupportRequest();
		assertThat(request).isNotNull();
		assertEquals(request.getRequestStatus(), "Accepted");
		request.setRequestStatus("Closed");
		try {
			request1 = requestService.saveRequest(request);
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		assertThat(request1).isNotNull();
		assertEquals(request1.getRequestStatus(), "Closed");	
	}	
	
	@Test
	void testFindClosedRequests() {
		List<SupportRequest> requests = requestService.findAllRequests();
		assertThat(requests).isNotNull().isNotEmpty();	
		
		List<SupportRequest> closedList = requests.stream()
				.filter(x -> x.getRequestStatus().equals("Closed"))				
				.collect(Collectors.toList());
		
		assertThat(closedList).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testFindRequestsByUserId() {
		List<SupportRequest> requests = requestService.findRequestsByUserId(6L);
		SupportRequest request = requestService.findRequestById(1L);
		assertThat(requests).isNotNull().isNotEmpty();	
		assertThat(requests.contains(request));
	}
	
	@Test
	void testFindRequestsByAdministratorId() {
		List<SupportRequest> requests = requestService.findRequestsByAdminId(31L);
		SupportRequest request = requestService.findRequestById(1L);
		assertThat(requests).isNotNull().isNotEmpty();	
		assertThat(requests.contains(request));
	}
	
	@Test
	void testRemoveRequest() {	
		assertThrows(NoSuchElementException.class, 
		() -> {
			requestService.removeRequest(1L);
			SupportRequest request;
			request = requestService.findRequestById(1L);			
		});
	}
	
	
}
