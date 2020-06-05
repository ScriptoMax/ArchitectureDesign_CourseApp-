package com.ahackannection.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.User;
import com.ahackannection.entity.user.Visitor;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.VisitorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/visitor")
@AllArgsConstructor
public class VisitorController {
	
	private final VisitorService visitorService;
	
	@PostMapping("/saveVisitor")
	@ResponseBody
	public Visitor saveVisitor(@RequestBody Visitor visitor) throws ValidationException {
		return visitorService.saveVisitor(visitor);
	}
	
	@GetMapping("/findVisitorsByCity")
	public List<Visitor> findVisitorsByCity(@RequestParam String city) {
		return visitorService.findVisitorsByCity(city);
	}
	
	@GetMapping("/findVisitorById")
	public Visitor findVisitorById(@RequestParam Long id) {
		return visitorService.findVisitorById(id);
	}
	
	@GetMapping("/findAllVisitors")
	public List<Visitor> findAllVisitors() {
		return visitorService.findAllVisitors();		
	}

}
