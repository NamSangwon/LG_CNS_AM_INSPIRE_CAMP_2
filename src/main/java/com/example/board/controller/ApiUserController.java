package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	@Autowired UserRepository userRepository;

	@CrossOrigin
	@PostMapping("/signup")
	public BodyBuilder signupPost(
		@RequestBody User user
	) {
		List<User> list = userRepository.findByEmailOrName(user.getEmail(), user.getName());
		boolean isEmpty = list.isEmpty();

		if (isEmpty) {
			userRepository.save(user);
		} 
		else {
			return ResponseEntity.badRequest();
		}

		return ResponseEntity.ok();
	}
}