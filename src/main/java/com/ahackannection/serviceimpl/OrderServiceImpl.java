package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Order;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.OrderRepository;
import com.ahackannection.service.OrderService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	@Autowired
	private final OrderRepository orderRepository;
	
	private void validateOrder(Order order) throws ValidationException {
		if(order == null) {
			throw new ValidationException("Object created is null");
		}		
	} 	
	
	public Order saveOrder(Order order) throws ValidationException {
		validateOrder(order);
		Order validOrder = orderRepository.save(order);
		return validOrder;	
	}
		
	public void addToFulfilledOrders(Long orderId) {
		Order order = orderRepository.findById(orderId).get();
		order.setStatus("Fulfilled");
		orderRepository.save(order);			
	}
	
	public void removeOrder(Long orderId) {
		orderRepository.deleteById(orderId);
	}
	
	public Order findOrderById(Long orderId) {		
		Order order = orderRepository.findById(orderId).get();
		return order;	
	}
	
	public List<Order> findOrdersByVisitorId(Long visitorId) {		
		List<Order> orderList = Lists.newArrayList(orderRepository.findAll());
		
		return orderList.stream()
				.filter(x -> x.getVisitor().getId() == visitorId)
				.collect(Collectors.toList());
	}
	
	public List<Order> findOrdersBySum(double lowerBound, double upperBound) {		
		List<Order> orderList = Lists.newArrayList(orderRepository.findAll());
		
		return orderList.stream()
				.filter(x -> x.getSum() >= lowerBound && x.getSum() <= upperBound)
				.collect(Collectors.toList());
	}
	
	public List<Order> findOrdersFulfilled() {
		List<Order> orderList = Lists.newArrayList(orderRepository.findAll());
		
		return orderList.stream()
				.filter(x -> x.getStatus().equals("Fulfilled"))
				.collect(Collectors.toList());
	}
	
	public List<Order> findAllOrders() {
		
		List<Order> orderList = Lists.newArrayList(orderRepository.findAll());
		
		return orderList.stream()				
				.collect(Collectors.toList());
	}

}
