package com.hackannection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hackannection.servicetest.UserServiceTest;

@SpringBootTest
class HackannectionApplicationTests {
	
	private UserServiceTest userTest; 

	@Test
	void contextLoads() {
		
		userTest.testFindAllUsers();
	}

}
