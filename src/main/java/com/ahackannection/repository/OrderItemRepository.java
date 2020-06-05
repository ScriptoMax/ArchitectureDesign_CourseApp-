package com.ahackannection.repository;

import org.springframework.data.repository.CrudRepository;

import com.ahackannection.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long>  {
	
}