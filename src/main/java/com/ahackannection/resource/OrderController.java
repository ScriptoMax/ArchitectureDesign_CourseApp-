package com.ahackannection.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.Order;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping("/saveOrder")
	@ResponseBody
	public Order saveOrder(@RequestBody Order order) throws ValidationException {
		return orderService.saveOrder(order);
	}
	
	@PutMapping("/addToFulfilled")
	public void addToFulfilledOrders(Long orderId) {
		orderService.addToFulfilledOrders(orderId);
	}
	
	@GetMapping("/findById")
	public Order findOrderById(@RequestParam Long orderId) {
		return orderService.findOrderById(orderId);
	}
	
	@GetMapping("/findBySum")
	public List<Order> findOrdersBySum(@RequestParam double lowerBound, @RequestParam double upperBound) {
		return orderService.findOrdersBySum(lowerBound, upperBound);
	}
	
	@GetMapping("/findByVisitorId")
	public List<Order> findOrdersByVisitorId(@RequestParam Long visitorId) {
		return orderService.findOrdersByVisitorId(visitorId);
	}
	
	@GetMapping("/findFulfilledOrders")
	public List<Order> findOrdersFulfilled() {
		return orderService.findOrdersFulfilled();
	}
	
	@GetMapping("/findAllOrders")
	public List<Order> findAllOrders() {
		return orderService.findAllOrders();
	}
	
	@DeleteMapping("/removeOrder/{orderId}")
	public ResponseEntity<Void> removeOrder(@PathVariable Long orderId) throws ValidationException {
		orderService.removeOrder(orderId);
		return ResponseEntity.ok().build();
	}
}
