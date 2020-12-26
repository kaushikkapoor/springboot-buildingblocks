package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public User createUser(User user) {
		return repository.save(user);
	}

	public User getUserById(Long id) throws Exception {
		Optional<User> opt = repository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("Entity not found");
	}

	public User updateUserById(Long id, User user) {
		user.setId(id);
		return repository.save(user);
	}

	public void deleteUserById(Long id) throws Exception{
		Optional<User> opt=repository.findById(id);
		if(opt.isPresent())
			repository.deleteById(id);
		throw new Exception("Row not found");
	}
	 public User getUserByUsername(String username) throws Exception{
		 Optional<User> opt=repository.findByUsername(username);
		 if(opt.isPresent())
			 return opt.get();
		 throw new Exception("Not User Found");
	 }

}
