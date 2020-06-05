package com.ahackannection.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.user.Administrator;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.AdministratorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdministratorController {
	
private final AdministratorService administratorService;
	
	@PostMapping("/saveAdministrator")
	//@ResponseBody
	public Administrator saveAdministrator(@RequestBody Administrator administrator) throws ValidationException {
		return administratorService.saveAdministrator(administrator);
	}
	
	@GetMapping("/findAllAdministrators")
	public List<Administrator> findAllAdministrators() {
		return administratorService.findAllAdministrators();		
	}

}
