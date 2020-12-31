package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public User createUser(User user) throws UserExistsException {
		Optional<User> existingUser = repository.findByUsername(user.getUsername());
		if (existingUser.isPresent())
			throw new UserExistsException("User Already Exists");
		return repository.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> opt = repository.findById(id);
		if (opt.isPresent()) {
			return opt;
		}
		throw new UserNotFoundException("Entity not found in User Repository");
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> opt = repository.findById(id);
		if (opt.isPresent()) {
			user.setUserId(id);
			return repository.save(user);
		}
		throw new UserNotFoundException("Entity not found in User Repository, provide the correct id");
	}

	public void deleteUserById(Long id) {
		Optional<User> opt = repository.findById(id);
		if (opt.isPresent())
			repository.deleteById(id);
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Entity not found in User Repository, provide the correct id");
	}

	public User getUserByUsername(String username) throws UserNameNotFoundException {
		Optional<User> opt = repository.findByUsername(username);
		if (opt.isPresent())
			return opt.get();
		throw new UserNameNotFoundException("Not User Found");
	}

}
