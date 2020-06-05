package com.hackannection.servicetest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.util.List;

import org.assertj.core.internal.Classes;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.User;
import com.ahackannection.service.UserService;

import lombok.AllArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@AllArgsConstructor
public class UserServiceTest {

	
	@Autowired
	final private UserService userService;
	
	@Test
	public void testFindAllUsers() {
		List<User> userList = userService.findAllUsers();
		assertThat(userList).isNotNull().isNotEmpty();
	}
	
	@Test
	public void testFindUsersByLogin(String login) {
		User user = userService.findUserByLogin("key");
		assertThat(user).isNotNull();
	}
}
