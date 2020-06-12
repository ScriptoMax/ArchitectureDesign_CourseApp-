package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.User;
import com.ahackannection.repository.UserRepository;
import com.ahackannection.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
class UserTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	void testFindAllUsers() {
		List<User> users = userService.findAllUsers();
		assertThat(users).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testFindUserById() {		
		User user = userService.findUserById(53L);		
		assertThat(user).isNotNull();
		String userLogin = user.getLogin();
		assertThat(userLogin).isEqualTo("generis");
	}
	
	@Test
	void testFindUserByLogin() {		
		User user = userService.findUserByLogin("key");
		assertThat(user).isNotNull();
		assertThat(user.getRole()).isEqualTo("Administrator");
	}
	
	@Test
	void testFindUserByEmail() {
		User user = userService.findUserById(47L);
		assertThat(user).isNotNull();
		String email = user.getEmail();
		assertThat(email).isEqualTo("marchez@gmail.com");
	}
	
	@Test
	void testIsCorporateUser() {
		User user = userService.findUserById(33L);
		assertThat(user).isNotNull();
		assertTrue(user.isCorporate());		
	}
	
	@Test
	void testBlockUser() {
		User user = userService.findUserById(45L);
		assertThat(user).isNotNull();
		assertFalse(user.isCorporate());
		assertEquals(user.getUserStatus(), "Active");
		userService.blockUser(user.getId());
		user = userService.findUserById(45L);
		assertEquals(user.getUserStatus(), "Blocked");	
	}
	
	@Test
	void testFindUsersByCountry() {
		List<User> users = userService.findUsersByCountry("US");
		User user = userService.findUserById(44L);
		assertThat(users).isNotNull().isNotEmpty();	
		assertThat(users.contains(user));
	}
	
	@Test
	void testRemoveUser() {	
		assertThrows(NoSuchElementException.class, 
		() -> {
			userService.removeUser(43L);
			User user;
			user = userService.findUserById(43L);			
		});
	}
}
