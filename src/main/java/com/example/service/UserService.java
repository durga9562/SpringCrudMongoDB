package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	public User getById(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public User createUser(User user) {
		return repository.save(user);
	}
	
	public User updateUser(String id,User user) {
		User existuser=repository.findById(id).orElse(null);
		if(existuser!=null) {
			existuser.setName(user.getName());
			existuser.setEmail(user.getEmail());
			return repository.save(existuser);
		}
		return null;
	}
	
	public void deleteUser(String id) {
		repository.deleteById(id);
	}

}
