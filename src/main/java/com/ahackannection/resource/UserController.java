package com.ahackannection.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.User;
import com.ahackannection.entity.user.Visitor;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PutMapping("/blockUser")
	public void blockUser(@RequestParam Long userId) {
		userService.blockUser(userId);
	}
	
	@GetMapping("/findAll")
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}
	
	@GetMapping("/findUsersByCountry")
	public List<User> findUsersByCountry(@RequestParam String country) {
		return userService.findUsersByCountry(country);
	}
	
	@GetMapping("/findUserById")
	public User findUsersById(@RequestParam Long id) {
		return userService.findUserById(id);
	}
	
	@GetMapping("/findUserByLogin")
	public User findUserByLogin(@RequestParam String login) {
		return userService.findUserByLogin(login);
	}
	
	@GetMapping("/findUserByEmail")
	public User findUserByEmail(@RequestParam String email) {
		return userService.findUserByEmail(email);
	}
	
	@GetMapping("/findWhichCorporate")
	public List<User> findUsersByCorporate() {
		return userService.findUsersByCorporate();
	}
	
	@DeleteMapping("/removeUser/{userId}")
	public ResponseEntity<Void> removeUser(@PathVariable Long userId) {
		userService.removeUser(userId);
		return ResponseEntity.ok().build();
	}
	
	
}
