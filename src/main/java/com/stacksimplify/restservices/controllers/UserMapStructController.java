package com.stacksimplify.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.DTOs.UserMsDto;
import com.stacksimplify.restservices.mappers.UserMapper;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/mapstruct")
public class UserMapStructController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

	@GetMapping()
	public List<UserMsDto> getAllUsers() {
		return userMapper.usersToUserMsDtos(userRepository.findAll());
	}
}
