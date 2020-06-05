package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.OrderItem;
import com.ahackannection.exception.ValidationException;

@Service
@Component
public interface OrderItemService {

	OrderItem saveOrderItem(OrderItem order) throws ValidationException;
	
	OrderItem findOrderItemById(Long id);
	
	List<OrderItem> findAllItems();
}
