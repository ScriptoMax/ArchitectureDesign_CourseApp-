package com.ahackannection.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahackannection.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>  {	
	
	Order findOrderById(Long orderId);	
	
	
}