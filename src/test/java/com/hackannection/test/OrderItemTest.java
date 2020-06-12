package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.OrderItem;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.OrderItemService;
import com.ahackannection.service.OrderService;
import com.ahackannection.service.OrganiserService;
import com.ahackannection.service.TrainingService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class OrderItemTest {
	
	@Autowired
	private OrderItemService orderItemService; 
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TrainingService trainingService;
	
	
	@Test
	void testSaveOrderItem() {
		OrderItem newOrderItem = new OrderItem();
		OrderItem newOrderItemRet = new OrderItem(); 
		newOrderItem.setOrder(orderService.findOrderById(16L));
		newOrderItem.setTraining(trainingService.findTrainingById(14L));
		newOrderItem.setPrice(55);		
		
		try {
			newOrderItemRet = orderItemService.saveOrderItem(newOrderItem);
			assertThat(newOrderItemRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testFindItemById() {		
		OrderItem item = orderItemService.findOrderItemById(7L);
		assertThat(item).isNotNull();
		assertEquals(item.getTraining().getTitle(), "Introduction to cryptography and its applications");
	}
	
	@Test
	void testFindAllItems() {
		List<OrderItem> items = orderItemService.findAllItems();
		assertThat(items).isNotNull().isNotEmpty();		
	}
}
