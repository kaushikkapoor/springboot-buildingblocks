package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.Entities.Order;
import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;

	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent())
			return userOptional.get().getOrders();
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
	}

	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable Long userId, @RequestBody Order order) {
		User user = null;
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			user = userOptional.get();
			order.setUser(user);
			return orderRepository.save(order);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
	}

	@GetMapping("/{userId}/orders/{orderId}")
	public Order getOrderByOrderId(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId)
			throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			Optional<Order> orderOptional = orderRepository.findById(orderId);
			if (orderOptional.isPresent())
				return orderOptional.get();
			throw new UserNotFoundException("OrderId Not Found");
		}
		throw new UserNotFoundException("User Not Found");

	}

}
