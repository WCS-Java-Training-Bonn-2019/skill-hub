package com.wildcodeschool.skillhub.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.skillhub.form.UserForm;
import com.wildcodeschool.skillhub.form.UserSkillLevel;
import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.model.UserSkill;
import com.wildcodeschool.skillhub.service.SkillService;
import com.wildcodeschool.skillhub.service.UserService;
import com.wildcodeschool.skillhub.service.UserSkillService;

@Controller
public class ChangePwController {
	
	//definiere Variable
	private final UserService userService;
	private final SkillService skillService;
	private final UserSkillService userSkillService;
	
	//ChangePwController
	@Autowired
	public ChangePwController(UserService userService, SkillService skillService, UserSkillService userSkillService) {
		super();
		this.userService = userService;
		this.skillService = skillService;
		this.userSkillService = userSkillService;
	}
	
	@GetMapping("/changePw")
	public String getLoginPage() {

		return "changePw";
	}
	
	
	// Change Password
	@PostMapping("/perform_login")
	public String postUser(@ModelAttribute UserForm userForm,
			@RequestParam(name = "id", required = false) Long userId, Principal principal) {
		boolean isNewUser = userId == null;

		User user = new User();

		if (!isNewUser) {
			Optional<User> optionalUser = userService.getSingleUser(userId);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		//Set<Long> userSkillIds = user.getUserSkillIds();
		//List<UserSkillLevel> userSkillLevels = userForm.getUserSkillLevels();


		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// user.setPassword(userForm.getPassword());
		user.setPassword(passwordEncoder.encode(passwordForm.getPassword()));

		userService.updateUser(user);
		
		if ("admin".equals(principal.getName())) {
			return "redirect:/admin";
		}
		return "redirect:/user/profile";

	}
	
}










