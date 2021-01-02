package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.DTOs.UserMmDto;
import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping("/modelmapper")
public class UserModelMapperController {

	@Autowired
	UserService service;

	@Autowired
	ModelMapper mapper;

	@GetMapping("user/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> optional = service.getUserById(id);
			User user = optional.get();
			UserMmDto dto = mapper.map(user, UserMmDto.class);
			return dto;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

}
