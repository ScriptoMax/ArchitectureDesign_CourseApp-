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

import com.ahackannection.entity.SupportRequest;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.RequestService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/request")
@AllArgsConstructor
public class RequestController {
	
	private final RequestService requestService;
	
	@PostMapping("/saveRequest")
	@ResponseBody
	public SupportRequest saveRequest(@RequestBody SupportRequest request) throws ValidationException {
		return requestService.saveRequest(request);
	}
	
	@PutMapping("/closeRequest")
	public void closeRequest(@RequestParam Long requestId) {
		requestService.closeRequest(requestId);
	}
	
	@GetMapping("/findById")
	public SupportRequest findRequestById(@RequestParam Long requestId) {
		return requestService.findRequestById(requestId);
	}
	
	@GetMapping("/findByUserId")
	public List<SupportRequest> findRequestsByUserId(@RequestParam Long userId) {
		return requestService.findRequestsByUserId(userId);
	}
	
	@GetMapping("/findByAdminId")
	public List<SupportRequest> findRequestsByAdminId(Long administratorId) {
		return requestService.findRequestsByAdminId(administratorId);
	}
	
	@GetMapping("/findAll")
	public List<SupportRequest> findAllRequests() {
		return requestService.findAllRequests();
	}
	
	@DeleteMapping("/removeRequest/{requestId}")
	public ResponseEntity<Void> removeRequest(@PathVariable Long requestId) {
		requestService.removeRequest(requestId);
		return ResponseEntity.ok().build();
	}
}
