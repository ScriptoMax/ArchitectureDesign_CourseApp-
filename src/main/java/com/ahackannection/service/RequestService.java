package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.SupportRequest;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface RequestService {
	
	SupportRequest saveRequest(SupportRequest supportRequest) throws ValidationException;
    
	void removeRequest(Long supportRequestId);
	
	void closeRequest(Long requestId);
	
	SupportRequest findRequestById(Long requestId);
	
	List<SupportRequest> findRequestsByUserId(Long userId);
	
	List<SupportRequest> findRequestsByAdminId(Long administratorId);
	
	List<SupportRequest> findAllRequests();

}
