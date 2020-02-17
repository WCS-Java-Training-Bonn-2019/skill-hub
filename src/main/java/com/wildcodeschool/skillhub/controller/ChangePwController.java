package com.wildcodeschool.skillhub.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.skillhub.form.PasswordForm;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.service.UserService;

@Controller
public class ChangePwController {

	private final UserService userService;

	@Autowired
	public ChangePwController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/changePw")
	public String getLoginPage(PasswordForm passwordForm, HttpServletRequest request) {
		User user = getUser((long) 0, request);
		if (user == null) {
			return "redirect:/";
		}
		return "changePw";
	}
	
	@PostMapping("/password/upsert")
	public String postUser(@ModelAttribute PasswordForm passwordForm,
			@RequestParam(name = "id", required = false) Long userId, HttpServletRequest request) {

		User user = getUser(userId, request);

		if (user == null) {
			return "redirect:/";
		}

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setPassword(passwordEncoder.encode(passwordForm.getPassword()));

		userService.updateUser(user);

		if ("admin".equals(request.getUserPrincipal().getName())) {
			return "redirect:/admin";
		}

		return "redirect:/user/profile";

	}

	// Helper function to retrieve the user either from the principal or by userId,
	// if the user has ADMIN role
	private User getUser(Long userId, HttpServletRequest request) {
		Optional<User> optionalUser = Optional.empty();

		if (request != null && request.isUserInRole("ROLE_ADMIN")) {
			if (userId != null) {
				optionalUser = userService.getSingleUser(userId);
			}

		} else {
			if (request != null && request.getUserPrincipal() != null) {
				optionalUser = userService.getSingleUserByEmail(request.getUserPrincipal().getName());
			}
		}

		return optionalUser.orElse(null);
	}

}
