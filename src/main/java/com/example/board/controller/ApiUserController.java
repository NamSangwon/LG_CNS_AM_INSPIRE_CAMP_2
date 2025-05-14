package com.example.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public ResponseEntity<?> signupPost(
		@RequestBody User user
	) {
		List<User> list = userRepository.findByEmailOrName(user.getEmail(), user.getName());
		boolean isEmpty = list.isEmpty();


		Map<String, String> response = new HashMap<>();

		if (isEmpty) {
			userRepository.save(user);

			response.put("message", "회원 가입 실패");
		}
		else {
			response.put("message", "회원 가입 실패");

			return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
		}

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
	}
}