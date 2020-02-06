package com.wildcodeschool.skillhub.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.wildcodeschool.skillhub.repository.UserRepository;
import com.wildcodeschool.skillhub.service.SkillService;
import com.wildcodeschool.skillhub.service.UserService;
import com.wildcodeschool.skillhub.service.UserSkillService;

@Controller
public class UserController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	UserService userService;

	@Autowired
	SkillService skillService;

	@Autowired
	UserSkillService userSkillService;

	@GetMapping("/users/search")
	public String getUsersBySkillId(Model model, @RequestParam(name = "id", required = true) Long skillId) {
		model.addAttribute("users", userService.getUsersBySkillId(skillId));

		Skill skill = skillService.getSingleSkill(skillId);
		model.addAttribute("skill", skill);

		return "users/get_by_skill";
	}

	// TODO Remove or protect for admin use only
	// Show all users for debugging
	@GetMapping("/usersoverview")
	public String getAll(Model model) {

		model.addAttribute("users", userService.getUsers());

		return "users/get_all";
	}

	// TODO For testing only
	// Show edit user form
	@GetMapping("/user/edit")
	public String showEditUserForm(UserForm userForm, @RequestParam(name = "id", required = false) Long userId) {

		User user = new User();

		if (userId != null) {
			Optional<User> optionalUser = userService.getSingleUser(userId);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		userForm.setUser(user);

		Set<UserSkill> userSkills = user.getUserSkills();
		List<Skill> skills = skillService.getSkills();

		UserSkillLevel userSkillLevel;

		for (Skill skill : skills) {
			userSkillLevel = new UserSkillLevel(skill.getId(), skill.getName(), false, skill.getImageURL());
			for (UserSkill userSkill : userSkills) {
				if (skill.getId() == userSkill.getId().getSkillId()) {
					userSkillLevel.setChecked(true);
				}
			}
			userForm.getUserSkillLevels().add(userSkillLevel);
		}

		return "user/edit";
	}

	// Create a new user
	@GetMapping("/user/new")
	public String getUser2(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		return "user/edit";
	}

	// Update or insert a user
	@PostMapping("/user/upsert")
	public String postUser(@ModelAttribute UserForm userForm,
			@RequestParam(name = "id", required = false) Long userId) {
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

		user.setId(userForm.getId());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setZipCode(userForm.getZipCode());
		user.setCity(userForm.getCity());
		user.setDateOfBirth(userForm.getDateOfBirth());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setDescription(userForm.getDescription());
		user.setImageURL(userForm.getImageURL());

		if (isNewUser) {
			userService.createNewUser(user);
		} else {
			userService.updateUser(user);
		}

		return "redirect:/users";
	}

	// View a user
	@GetMapping("/user/view")
	public String viewUser(Model model, @RequestParam(name = "id", required = false) Long userId) {

		User user = new User();

		if (userId != null) {
			Optional<User> optionalUser = userService.getSingleUser(userId);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		model.addAttribute("user", user);

		return "user/view";
	}

	// Delete a user
	@GetMapping("/user/delete")
	public String deleteUser(@RequestParam Long id) {

		userService.deleteUser(id);

		return "redirect:/user/deleted";
	}

	// Delete a user
	@GetMapping("/user/deleted")
	public String deletedUser() {

		return "/user/deleted";
	}

}
