package com.stacksimplify.restservices.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@Validated
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	@PostMapping("/createuser")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builders) {
		try {
			service.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builders.path("users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@GetMapping("user/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return service.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@PutMapping("updateuser/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return service.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@DeleteMapping("deleteuser/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		service.deleteUserById(id);
	}

	@GetMapping("user/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws Exception {
		return service.getUserByUsername(username);
	}
}
