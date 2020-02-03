package com.wildcodeschool.skillhub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	SkillService skillService;

	@Autowired
	UserSkillService userSkillService;

	@GetMapping("/users/search")
	public String getUsersBySkillId(Model model, @RequestParam Long id) {
		model.addAttribute("users", userService.findByuserSkills_SkillId(id));

		Optional<Skill> optionalSkill = skillService.findById(id);

		if (optionalSkill.isPresent()) {
			model.addAttribute("skill", optionalSkill.get());
		}

		return "users/get_by_skill";
	}

	// TODO Remove or protect for admin use only
	// Show all users for debugging
	@GetMapping("/users")
	public String getAll(Model model) {

		model.addAttribute("users", userService.findAll());

		return "users/get_all";
	}

	// TODO For testing only
	// Show edit user form
	@GetMapping("/user/edit")
	public String showEditUserForm(UserForm userForm, @RequestParam(required = false) Long id) {

		User user = new User();

		if (id != null) {
			Optional<User> optionalUser = userService.findById(id);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		userForm.setUser(user);

		List<UserSkill> userSkills = user.getUserSkills();
		List<Skill> allSkills = skillService.findAll();

		UserSkillLevel userSkillLevel;

		for (int i = 0; i < allSkills.size(); i++) {
			userSkillLevel = new UserSkillLevel(allSkills.get(i).getId(), allSkills.get(i).getName(), false,
					allSkills.get(i).getImageURL());
			for (int j = 0; j < userSkills.size(); j++) {
				if (allSkills.get(i).getId() == userSkills.get(j).getId().getSkillId()) {
					userSkillLevel.setChecked(true);
				}
			}
			userForm.getUserSkillLevels().add(userSkillLevel);
		}

		System.out.println("==============================================================================");
		for (int i = 0; i < userSkills.size(); i++) {
			System.out.println("(Create) User Form: " + userForm.getUserSkillLevels().get(i).getName());
			System.out.println("==============================================================================");
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

	// Edit a user
//	@GetMapping("/user/edit")
//	public String getUser(Model model, @RequestParam(required = false) Long id) {
//
//		User user = new User();
//
//		if (id != null) {
//			Optional<User> optionalUser = userRepository.findById(id);
//			if (optionalUser.isPresent()) {
//				user = optionalUser.get();
//			}
//		}
//		
//		model.addAttribute("user", user);
//		return "user/edit";
//	}

//	// Create a new user
//	@GetMapping("/user/new")
//	public String getUser2(Model model) {
//		User user = new User();
//		model.addAttribute("user", user);
//
//		return "user/edit";
//
//	}

	// Update or insert a user
	@PostMapping("/user/upsert")
	public String postUser(@ModelAttribute UserForm userForm, @RequestParam(required = false) Long id) {
		User user = new User();

		if (id != null) {
			Optional<User> optionalUser = userService.findById(id);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		List<Long> userSkillIds = user.getUserSkillIds();
		List<UserSkillLevel> userSkillLevels = userForm.getUserSkillLevels();

		// Durchlaufen der UserSkillLevel-Liste - geht über alle skills
		for (UserSkillLevel userSkillLevel : userSkillLevels) {

			if (userSkillLevel.isChecked()) {
				Skill skill = null;

				Optional<Skill> optionalSkill = skillService.findById(userSkillLevel.getId());

				if (optionalSkill.isPresent()) {
					skill = optionalSkill.get();
				}

				if (!(userSkillIds.contains(userSkillLevel.getId()))) {
					userSkillService.addUserSkill(user, skill);
				}
			} else {
				Skill skill = null;

				Optional<Skill> optionalSkill = skillService.findById(userSkillLevel.getId());

				if (optionalSkill.isPresent()) {
					skill = optionalSkill.get();
				}

				if (userSkillIds.contains(userSkillLevel.getId())) {
					userSkillService.removeUserSkill(user, skill);
				}
			}
		}

		user.setId(userForm.getId());
		user.setUserName(userForm.getUserName());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setZipCode(userForm.getZipCode());
		user.setCity(userForm.getCity());
		user.setDateOfBirth(userForm.getDateOfBirth());
		user.setEmail(userForm.getEmail());
		user.setDescription(userForm.getDescription());
		user.setImageURL(userForm.getImageURL());

		userService.save(user);

		return "redirect:/users";
	}

	// View a user
	@GetMapping("/user/view")
	public String viewUser(Model model, @RequestParam(required = false) Long id) {

		User user = new User();

		if (id != null) {
			Optional<User> optionalUser = userService.findById(id);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		model.addAttribute("user", user);
		model.addAttribute("users", userService.findByuserSkills_SkillId(id));

		return "user/view";
	}

	// Delete a user
	@GetMapping("/user/delete")
	public String deleteUser(@RequestParam Long id) {

		userService.deleteById(id);

		return "redirect:/user/deleted";
	}

	// Delete a user
	@GetMapping("/user/deleted")
	public String deletedUser() {

		return "/user/deleted";
	}

}
