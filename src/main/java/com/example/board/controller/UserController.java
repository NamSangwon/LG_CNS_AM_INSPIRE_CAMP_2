package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.entity.User;
import com.example.board.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired UserRepository userRepository;

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	@PostMapping("/signin")
	public String signinPost(
		@ModelAttribute User user,
		HttpSession session
	) {
		List<User> list = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
		boolean isFindOnlyOne = (list.size() == 1);

		if (isFindOnlyOne) {
			session.setAttribute("user_info", list.get(0));
		}
		else {
			return "redirect:/signin";
		}

		return "redirect:/";
	}

	@GetMapping("/signout")
	public String signout(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}
	
	@GetMapping("/signup") 
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signupPost(
		@ModelAttribute User user,
		HttpSession session
	) {
		List<User> list = userRepository.findByEmailOrName(user.getEmail(), user.getName());
		boolean isEmpty = list.isEmpty();

		if (isEmpty) {
			userRepository.save(user);
		} 
		else {
			return "redirect:/signup";
		}

		return "redirect:/";
	}
}