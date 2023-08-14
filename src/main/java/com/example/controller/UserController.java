package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/v")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {
	@Autowired
	private UserService service;
	@GetMapping("/get")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users=service.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") String id){
		User user=service.getById(id);
		if(user!=null) {
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createuser=service.createUser(user);
		return new ResponseEntity<>(createuser,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@PathVariable("id") String id,@RequestBody User user){
		User updateuser=service.updateUser(id, user);
		if(updateuser!=null) {
			return new ResponseEntity<>(updateuser,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete")
	public ResponseEntity deleteUser(@PathVariable("id") String id){
		service.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
