package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Order;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface OrderService {
	
	Order saveOrder(Order order) throws ValidationException;
    
	void removeOrder(Long orderId) throws ValidationException;
	
	void addToFulfilledOrders(Long orderId);
	
	Order findOrderById(Long orderId);
		
	List<Order> findOrdersByVisitorId(Long visitorId);
		
	List<Order> findOrdersFulfilled();
	 
	List<Order> findOrdersBySum(double lowerBound, double upperBound);	
		
    List<Order> findAllOrders();

}
