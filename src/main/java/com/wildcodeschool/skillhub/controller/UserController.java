package com.wildcodeschool.skillhub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wildcodeschool.skillhub.repository.SkillRepository;
import com.example.validatingforminput.PersonForm;
import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.model.UserForm;
import com.wildcodeschool.skillhub.model.UserSkill;
import com.wildcodeschool.skillhub.model.UserSkillLevel;
import com.wildcodeschool.skillhub.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SkillRepository skillRepository;

	@GetMapping("/users/search")
	public String getBySkill(Model model, @RequestParam Long id) {
		model.addAttribute("users", userRepository.findByuserSkills_SkillId(id));

		Optional<Skill> optionalSkill = skillRepository.findById(id);

		if (optionalSkill.isPresent()) {
			model.addAttribute("skill", optionalSkill.get());
		}

		return "users/get_by_skill";
	}

	// TODO Remove or protect for admin use only
	// Show all users for debugging
	@GetMapping("/users")
	public String getAll(Model model) {

		model.addAttribute("users", userRepository.findAll());

		return "users/get_all";
	}

	// Show edit user form
	@GetMapping("/test")
	public String showEditUser(UserForm userForm) {
		return "user/test";
	}	
	
	// Edit a user
	@GetMapping("/user/edit")
	public String getUser(Model model, @RequestParam(required = false) Long id) {

		User user = new User();

		if (id != null) {
			Optional<User> optionalUser = userRepository.findById(id);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		UserForm userForm = new UserForm ();
		userForm.setUser(user);

		List<UserSkill> userSkills = user.getUserSkills();
		System.out.println("--> Ab hier checken! ========================================================================================================");
		System.out.println("----------------> User Skills: " +userSkills.get(0).getSkill().toString());
		System.out.println("-----N A M E -----------> Name mit User Skills: " + user.getFirstName());
		List<Skill> completeList = skillRepository.findAll();
		System.out.println("complete List: " + completeList.toString());

		UserSkillLevel userSkillLevel;

		for (int i = 0; i < completeList.size(); i++) {
			userSkillLevel = new UserSkillLevel(false, completeList.get(i).getName(), completeList.get(i).getId());
			for (int j = 0; j < userSkills.size(); j++) {
				if (completeList.get(i).getId() == userSkills.get(j).getId().getSkillId()) {
					userSkillLevel.setHasSkill(true);
				}
			}
			userForm.getUserSkillLevel().add(userSkillLevel);
		}
		System.out.println("------> User From: " + userForm.getUserSkillLevel().toString());
		
		model.addAttribute("user", user);
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
	public String postUser(@ModelAttribute User user) {

		userRepository.save(user);

		return "redirect:/users";
	}

	// View a user
	@GetMapping("/user/view")
	public String viewUser(Model model, @RequestParam(required = false) Long id) {

		User user = new User();

		if (id != null) {
			Optional<User> optionalUser = userRepository.findById(id);
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

		userRepository.deleteById(id);

		return "redirect:/users";
	}

}
