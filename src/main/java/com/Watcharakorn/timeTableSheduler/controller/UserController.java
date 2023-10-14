package com.Watcharakorn.timeTableSheduler.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Watcharakorn.timeTableSheduler.model.User;
import com.Watcharakorn.timeTableSheduler.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public ResponseEntity<Object> getAllUser() {
		System.out.println("1");
		try {
			List<User> users = userRepository.findAll();
			if (!users.isEmpty()) {
				return new ResponseEntity<>(users, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("user is empty!", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal sever error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<Object> authenticateUser(@RequestParam String username, @RequestParam String password) {
		System.out.println("1");
		try {
			System.out.println("In try cath");
			boolean validate = false;
			System.out.println("befor find by username");
			List<User> users = userRepository.findByUserNameEquals(username);
			User user = null;
			if (!users.isEmpty()) {
				for (int i = 0; i < users.size(); i++) {
					validate = users.get(i).getUserPassword().equals(password);
					if (validate) {
						user = users.get(i);
						break;
					}
				}
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println("1");
			return new ResponseEntity<>("Internal sever error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<Object> createNewAccount(@RequestBody User bodyUser) {
		try {
			userRepository.save(bodyUser);
			return new ResponseEntity<>("create new account success!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("bad request!", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Object> editUser(@PathVariable Integer idInteger, @RequestBody User bodyUser) {
		try {
			Optional<User> user = userRepository.findById(idInteger);
			if (user.isPresent()) {
				user.get().setUserName(bodyUser.getUserName());
				user.get().setUserPassword(bodyUser.getUserPassword());
				userRepository.save(user.get());
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("id not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("bad request!", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer idInteger) {
		try {
			Optional<User> user = userRepository.findById(idInteger);
			if (user.isPresent()) {
				userRepository.deleteById(idInteger);
				return new ResponseEntity<>("delete success!", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("id not found!", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("internal sever error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
