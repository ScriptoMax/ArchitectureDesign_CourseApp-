package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.SupportRequest;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.RequestRepository;
import com.ahackannection.service.RequestService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private final RequestRepository requestRepository;
	
	private void validateRequest(SupportRequest supportRequest) throws ValidationException {
		if(supportRequest == null) {
			throw new ValidationException("Object created is null");
		}		
	} 	
	
	public SupportRequest saveRequest(SupportRequest supportRequest) throws ValidationException {
		validateRequest(supportRequest);
		SupportRequest validRequest = requestRepository.save(supportRequest);
		return validRequest;	
	}
	
	public void closeRequest(Long requestId) {
		SupportRequest req = requestRepository.findById(requestId).get();
		req.setRequestStatus("CLOSE");
		requestRepository.save(req);			
	}
	
	public void removeRequest(Long requestId) {
		requestRepository.deleteById(requestId);
	}
	
	public SupportRequest findRequestById(Long requestId) {		
		SupportRequest request = requestRepository.findById(requestId).get();
		
		return request; 
	}
	
	public List<SupportRequest> findRequestsByUserId(Long userId) {		
		List<SupportRequest> requestList = Lists.newArrayList(requestRepository.findAll());
		
		return requestList.stream()
				.filter(x -> x.get_user().getId() == userId)
				.collect(Collectors.toList());
	}
	
	public List<SupportRequest> findRequestsByAdminId(Long administratorId) {		
		List<SupportRequest> requestList = Lists.newArrayList(requestRepository.findAll());
		
		return requestList.stream()
				.filter(x -> x.getAdmin().getId() == administratorId)
				.collect(Collectors.toList());
	}
	
	public List<SupportRequest> findAllRequests() {
		
		List<SupportRequest> requestList = Lists.newArrayList(requestRepository.findAll());
		
		return requestList.stream()				
				.collect(Collectors.toList());
	}	
}
