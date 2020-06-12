package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.Order;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.OrderService;
import com.ahackannection.service.VisitorService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class OrderTest {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private VisitorService visitorService;

	@Test
	void testSaveOrder() {
		Order newOrder = new Order();
		Order newOrderRet = new Order(); 
		newOrder.setStatus("Received");
		newOrder.setVisitor(visitorService.findVisitorById(46L));
		newOrder.setItemsContained((byte) 1);
		newOrder.setSum(30);		
		
		try {
			newOrderRet = orderService.saveOrder(newOrder);
			assertThat(newOrderRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testAddOrderToFulfilled() {
		Order order = orderService.findOrderById(7L);
		Order order1 = new Order();
		assertThat(order).isNotNull();
		assertEquals(order.getStatus(), "Accepted");
		order.setStatus("Fulfilled");
		try {
			order1 = orderService.saveOrder(order);
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		assertThat(order1).isNotNull();
		assertEquals(order1.getStatus(), "Fulfilled");	
	}		
	
	@Test
	void testFindFulfilledOrders() {
		List<Order> orders = orderService.findAllOrders();
		assertThat(orders).isNotNull().isNotEmpty();	
		
		List<Order> accceptedList = orders.stream()
				.filter(x -> x.getStatus().equals("Fulfilled"))				
				.collect(Collectors.toList());
		
		assertThat(accceptedList).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testFindOrdersByVisitorId() {
		List<Order> orders = orderService.findOrdersByVisitorId(35L);
		Order order = orderService.findOrderById(1L);
		assertThat(orders).isNotNull().isNotEmpty();	
		assertThat(orders.contains(order));
	}
	
	@Test
	void testFindOrdersBySum() {
		List<Order> orders = orderService.findOrdersBySum(50, 200);
		Order order = orderService.findOrderById(8L);
		assertThat(orders).isNotNull().isNotEmpty();	
		assertThat(orders.contains(order));
	}
	
	@Test
	void testFindOrderById() {		
		Order order = orderService.findOrderById(24L);
		assertThat(order).isNotNull();
		assertEquals(order.getVisitor().getLogin(), "enter");
	}
	
	@Test
	void testFindAllOrders() {
		List<Order> orders = orderService.findAllOrders();
		assertThat(orders).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testRemoveOrder() {	
		assertThrows(NoSuchElementException.class, 
		() -> {
			orderService.removeOrder(22L);
			Order order;
			order = orderService.findOrderById(22L);			
		});
	}
}