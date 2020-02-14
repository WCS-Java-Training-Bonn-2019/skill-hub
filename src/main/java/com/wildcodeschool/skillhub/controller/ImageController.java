package com.wildcodeschool.skillhub.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.service.UserService;

@Controller
public class ImageController {

	private final UserService userService;

	@Autowired
	public ImageController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/avatar/image")
	public ResponseEntity<byte[]> loadImageAvatar(HttpServletRequest request) {

		User user = getUser((long) 0, request);

		if (user.getImage() != null) {
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(user.getImage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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