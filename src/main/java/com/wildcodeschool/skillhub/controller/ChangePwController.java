package com.wildcodeschool.skillhub.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.skillhub.form.UserForm;
import com.wildcodeschool.skillhub.form.UserSkillLevel;
import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;

@Controller
public class ChangePwController {
	
	@GetMapping("/changePw")
	public String getLoginPage() {

		return "changePw";
	}
	
	
	
	
	
	
	
	// Change Password
	@PostMapping("/user/upsert")
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

		Set<Long> userSkillIds = user.getUserSkillIds();
		List<UserSkillLevel> userSkillLevels = userForm.getUserSkillLevels();

		// Durchlaufen der UserSkillLevel-Liste - geht über alle skills
		for (UserSkillLevel userSkillLevel : userSkillLevels) {

			if (userSkillLevel.isChecked()) {
				Skill skill;

				skill = skillService.getSingleSkill(userSkillLevel.getId());

				if (!(userSkillIds.contains(userSkillLevel.getId()))) {
					userSkillService.addNewUserSkill(user, skill);
				}
			} else {
				Skill skill = null;

				skill = skillService.getSingleSkill(userSkillLevel.getId());

				if (userSkillIds.contains(userSkillLevel.getId())) {
					userSkillService.removeUserSkill(user, skill);
				}
			}
		}

		//--> PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setId(userForm.getId());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setZipCode(userForm.getZipCode());
		user.setCity(userForm.getCity());
		user.setDateOfBirth(userForm.getDateOfBirth());
		user.setEmail(userForm.getEmail());
		// user.setPassword(userForm.getPassword());
		//--> user.setPassword(passwordEncoder.encode(userForm.getPassword()));

		user.setDescription(userForm.getDescription());
		user.setImageURL(userForm.getImageURL());

		if (isNewUser) {
			userService.createNewUser(user);
		} else {
			userService.updateUser(user);
		}
		
		if ("admin".equals(principal.getName())) {
			return "redirect:/admin";
		}
		return "redirect:/user/profile";

	}
	
	
	
	
	
}










