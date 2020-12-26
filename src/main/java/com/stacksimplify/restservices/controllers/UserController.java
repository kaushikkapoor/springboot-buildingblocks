package com.stacksimplify.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	@PostMapping("/createuser")
	public User createUser(@RequestBody User user) {
		return service.createUser(user);
	}

	@GetMapping("user/{id}")
	public User getUserById(@PathVariable("id") Long id) throws Exception {
		return service.getUserById(id);
	}

	@PutMapping("updateuser/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		return service.updateUserById(id, user);
	}

	@DeleteMapping("deleteuser/{id}")
	public void deleteUserById(@PathVariable("id") Long id) throws Exception{
		service.deleteUserById(id);
	}
	
	@GetMapping("user/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws Exception{
		return service.getUserByUsername(username);
	}
}
