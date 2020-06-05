package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.Order;
import com.ahackannection.entity.OrderItem;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.OrderItemRepository;
import com.ahackannection.service.OrderItemService;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private final OrderItemRepository orderItemRepository;
	
	private void validateOrderItem(OrderItem orderItem) throws ValidationException {
		if(orderItem == null) {
			throw new ValidationException("Object created is null");
		}		
	} 	
	
	public OrderItem saveOrderItem(OrderItem orderItem) throws ValidationException {
		validateOrderItem(orderItem);
		OrderItem validOrderItem = orderItemRepository.save(orderItem);
		return validOrderItem;	
	}
	
	public OrderItem findOrderItemById(Long id) {		
		OrderItem orderItem = orderItemRepository.findById(id).get();
		return orderItem;	
	}
	
	public List<OrderItem> findAllItems() {
		
		List<OrderItem> itemList = Lists.newArrayList(orderItemRepository.findAll());
		
		return itemList.stream()				
				.collect(Collectors.toList());
	}

}
