package com.wildcodeschool.skillhub.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wildcodeschool.skillhub.form.UserForm;
import com.wildcodeschool.skillhub.form.UserSkillLevel;
import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.service.SkillService;
import com.wildcodeschool.skillhub.service.UserService;

@Controller
public class RegisterController {

	private final UserService userService;
	private final SkillService skillService;

	@Autowired
	public RegisterController(UserService userService, SkillService skillService) {
		super();
		this.userService = userService;
		this.skillService = skillService;
	}

	// Show registration page
	@GetMapping("/register")
	public String showRegisterForm(UserForm userForm) {
		List<Skill> skills = skillService.getAllSkills();

		for (Skill skill : skills) {
			UserSkillLevel userSkillLevel = new UserSkillLevel(skill.getId(), skill.getName(), false,
					skill.getImageURL());

			userForm.getUserSkillLevels().add(userSkillLevel);
		}

		return "register";
	}

	// Register an login a new user
	@PostMapping("/register")
	public String postUser(@ModelAttribute UserForm userForm, HttpServletRequest request,
			HttpServletResponse response) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = new User();

		// Email Validation
		if (userService.emailExists(userForm.getEmail())) {
			return "emailExists";
		}

		user.setId(userForm.getId());
		user.setEmail(userForm.getEmail());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setZipCode(userForm.getZipCode());
		user.setCity(userForm.getCity());
		user.setDateOfBirth(userForm.getDateOfBirth());
		user.setDescription(userForm.getDescription());

		if (userForm.getImage().length != 0) {
			user.setImage(userForm.getImage());
		} else {
			user.setImageURL("avatar.png");
		}

		List<UserSkillLevel> userSkillLevels = userForm.getUserSkillLevels();

		for (UserSkillLevel userSkillLevel : userSkillLevels) {

			if (userSkillLevel.isChecked()) {
				user.addSkill(skillService.getSingleSkillById(userSkillLevel.getId()).get());
			}
		}

		user = userService.createNewUser(user);

		// Login the user if it was not the admin
		if (request != null && request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin";
		} else {
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(auth);

			return "redirect:/";
		}

	}

}
