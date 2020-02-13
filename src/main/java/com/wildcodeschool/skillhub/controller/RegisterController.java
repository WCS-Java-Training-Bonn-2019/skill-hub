package com.wildcodeschool.skillhub.controller;

import java.util.List;

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
import com.wildcodeschool.skillhub.service.UserSkillService;

@Controller
public class RegisterController {

	private final UserService userService;
	private final SkillService skillService;
	private final UserSkillService userSkillService;

	@Autowired
	public RegisterController(UserService userService, SkillService skillService, UserSkillService userSkillService) {
		super();
		this.userService = userService;
		this.skillService = skillService;
		this.userSkillService = userSkillService;
	}

	// Show registration page
	@GetMapping("/register")
	public String showRegisterForm(UserForm userForm) {

		return "register";
	}

	// Register an login a new user
	@PostMapping("/register")
	public String postUser(@ModelAttribute UserForm userForm) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = new User();
		
		//Email Validation
		if (userService.emailExists(userForm.getEmail())) {
			return "emailExists";
		}
		
		List<UserSkillLevel> userSkillLevels = userForm.getUserSkillLevels();

		for (UserSkillLevel userSkillLevel : userSkillLevels) {

			if (userSkillLevel.isChecked()) {
				Skill skill = skillService.getSingleSkill(userSkillLevel.getId());

				userSkillService.addNewUserSkill(user, skill);
			}
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
		user.setImage(userForm.getImage());

		user = userService.createNewUser(user);

		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(auth);

		return "redirect:/";

	}

}
