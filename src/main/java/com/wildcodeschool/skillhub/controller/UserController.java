package com.wildcodeschool.skillhub.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.skillhub.form.UserForm;
import com.wildcodeschool.skillhub.form.UserSkillLevel;
import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.model.UserSkill;
import com.wildcodeschool.skillhub.service.SkillService;
import com.wildcodeschool.skillhub.service.UserService;

@Controller
public class UserController {

	private final UserService userService;
	private final SkillService skillService;

	@Autowired
	public UserController(UserService userService, SkillService skillService) {
		super();
		this.userService = userService;
		this.skillService = skillService;
	}
	
	@GetMapping("/user/{userId}/image")
	public ResponseEntity<byte[]> loadImage(@PathVariable Long userId){
		
		Optional<User> optionalUser = userService.getSingleUserById(userId);
		if (optionalUser.isPresent() && optionalUser.get().getImage() != null) {
			User user = optionalUser.get();
			
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.IMAGE_PNG)
					.body(user.getImage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.build();
	}

	// Show users with a certain skill
	@GetMapping("/users/search")
	public String getUsersBySkillId(Model model, @RequestParam(name = "id", required = true) Long skillId) {
		Skill skill = skillService.getSingleSkillById(skillId).get();

		model.addAttribute("users", userService.getUsersWithSkill(skill));
		model.addAttribute("skill", skill);

		return "users/get_by_skill";
	}

	// Show administrator page
	@GetMapping("/admin")
	public String getAll(Model model) {

		model.addAttribute("users", userService.getAllUsers());

		return "users/get_all";
	}

	// Show edit user form
	@GetMapping("/user/edit")
	public String showEditUserForm(UserForm userForm, @RequestParam(name = "id", required = false) Long userId,
			HttpServletRequest request) {
		User user = getUser(userId, request);

		if (user == null) {
			return "redirect:/";
		}

		userForm.setUser(user);

		Set<UserSkill> userSkills = user.getUserSkills();
		List<Skill> skills = skillService.getAllSkills();

		UserSkillLevel userSkillLevel;

		for (Skill skill : skills) {
			userSkillLevel = new UserSkillLevel(skill.getId(), skill.getName(), false, skill.getImageURL());

			for (UserSkill userSkill : userSkills) {
				if (skill.getId() == userSkill.getSkill().getId()) {
					userSkillLevel.setChecked(true);
				}
			}
			userForm.getUserSkillLevels().add(userSkillLevel);
		}

		return "user/edit";
	}

	// Update an user
	@PostMapping("/user/upsert")
	public String postUser(@ModelAttribute UserForm userForm, @RequestParam(name = "id", required = false) Long userId,
			HttpServletRequest request) {
		User user = getUser(userId, request);

		if (user == null) {
			return "redirect:/";
		}

		// Get E-Mail from Principal
		String email = user.getEmail();

		// Email Validation
		if (userService.emailExists(userForm.getEmail())) {
			if (!(email.equals(userForm.getEmail()))) {
				return "emailExists";
			}
		}

		Set<UserSkill> userSkills = user.getUserSkills();
		List<UserSkillLevel> userSkillLevels = userForm.getUserSkillLevels();

		for (UserSkillLevel userSkillLevel : userSkillLevels) {
			Skill skill = skillService.getSingleSkillById(userSkillLevel.getId()).get();
			UserSkill userSkill = UserSkill.builder().user(user).skill(skill).build();

			if (userSkillLevel.isChecked()) {
				if (!(userSkills.contains(userSkill))) {
					user.addSkill(skill);
				}
			} else {
				if (userSkills.contains(userSkill)) {
					user.removeSkill(skill);
				}
			}
		}

		user.setId(userForm.getId());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setZipCode(userForm.getZipCode());
		user.setCity(userForm.getCity());
		user.setDateOfBirth(userForm.getDateOfBirth());
		user.setEmail(userForm.getEmail());
		user.setDescription(userForm.getDescription());

		if (userForm.getImage().length != 0) {
			user.setImage(userForm.getImage());
		}

		userService.updateUser(user);

		if ("admin".equals(request.getUserPrincipal().getName())) {
			return "redirect:/admin";
		}

		return "redirect:/user/profile";

	}

	// View a user
	@GetMapping("/user/view")
	public String viewUser(Model model, @RequestParam(name = "id", required = false) Long userId) {

		User user = new User();

		if (userId != null) {
			Optional<User> optionalUser = userService.getSingleUserById(userId);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		model.addAttribute("user", user);

		return "user/view";
	}

	// View user profile
	@GetMapping("/user/profile")
	public String viewProfile(Model model, @RequestParam(name = "id", required = false) Long userId,
			HttpServletRequest request) {
		User user = getUser(userId, request);

		if (user == null) {
			return "redirect:/";
		}

		model.addAttribute("user", user);

		return "user/profile";
	}

	// Delete a user
	@GetMapping("/user/delete")
	public String deleteUser(@RequestParam(name = "id", required = false) Long userId, HttpServletRequest request) {
		User user = getUser(userId, request);

		if (user == null) {
			return "redirect:/";
		}

		userService.deleteUser(user);

		return "redirect:/user/deleted";
	}

	// Delete a user
	@GetMapping("/user/deleted")
	public String deletedUser() {

		return "/user/deleted";
	}

	// Helper function to retrieve the user either from the principal or by userId,
	// if the user has ADMIN role
	private User getUser(Long userId, HttpServletRequest request) {
		Optional<User> optionalUser = Optional.empty();

		if (request != null && request.isUserInRole("ROLE_ADMIN")) {
			if (userId != null) {
				optionalUser = userService.getSingleUserById(userId);
			}

		} else {
			if (request != null && request.getUserPrincipal() != null) {
				optionalUser = userService.getSingleUserByEmail(request.getUserPrincipal().getName());
			}
		}

		return optionalUser.orElse(null);
	}

}