package com.ahackannection.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.OrderItem;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.OrderItemService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orderItem")
@AllArgsConstructor
public class OrderItemController {
	
private final OrderItemService orderItemService;
	
	@PostMapping("/saveOrderItem")
	@ResponseBody
	public OrderItem saveOrderItem(@RequestBody OrderItem orderItem) throws ValidationException {
		return orderItemService.saveOrderItem(orderItem);
	}
	
	@GetMapping("/findById")
	public OrderItem findOrderItemById(@RequestParam Long id) {
		return orderItemService.findOrderItemById(id);
	}
	
	@GetMapping("/findAllItems")
	public List<OrderItem> findAllItems() {
		return orderItemService.findAllItems();
	}
}
