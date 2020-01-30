package com.wildcodeschool.skillhub.controller;

import java.util.Date;
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
import com.wildcodeschool.skillhub.repository.SkillRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import com.wildcodeschool.skillhub.repository.UserSkillRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private UserSkillRepository userSkillRepository;

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

	// TODO For testing only
	// Show edit user form
	@GetMapping("/user/edit")
	public String showEditUserForm(UserForm userForm, @RequestParam(required = false) Long id) {

		User user = new User();

		if (id != null) {
			Optional<User> optionalUser = userRepository.findById(id);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		userForm.setUser(user);

		List<UserSkill> userSkills = user.getUserSkills();
		List<Skill> allSkills = skillRepository.findAll();

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
	public String postUser(UserForm userForm, @RequestParam(required = false) Long id) {
		User user = new User();
		System.out.println("ML START...======================================>");
		System.out.println("userForm-getFirstName in upsert: " + userForm.getFirstName());
		//System.out.println("(Create) User Form: " + userForm.getUserSkillLevels().get(0).getName());

		if (id != null) {
			Optional<User> optionalUser = userRepository.findById(id);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}

		List<UserSkill> userSkills = user.getUserSkills();
		List<UserSkillLevel> userSkillLevels = userForm.getUserSkillLevels();
		
		System.out.println("ML: userForm, getUserSkill-Level" +  userForm.getUserSkillLevels());
		
		System.out.println("ML: userSkillLevel: ...nicht vorhanden...  >>> " + userSkillLevels);
		
		// Problem: UserSkillLevel-Liste ist leer...!
		
		
		
		
		

		System.out.println("==============================================================================");
		for (int i = 0; i < userSkills.size(); i++) {
			System.out.println("User Skills: " + userSkills.get(i).getSkill().getName());
			
		}
		System.out.println("Start ==============================================================================");
		for (int i = 0; i < userSkillLevels.size(); i++) {
			System.out.println("User Form: " + userSkillLevels.get(i).getName());
		}
		System.out.println("Stopp==============================================================================");

		
		
		
		
		// Durchlaufen der UserSkillLevel-Liste - geht über alle skills
		for (UserSkillLevel userSkillLevel : userSkillLevels) {
			System.out.println("ML: userSkillLevel: " + userSkillLevel + " >>> userSkillLevels " + userSkillLevels + " (Schleife) ...");

			if (userSkillLevel.isChecked()) {
				System.out.println("ML-1: ---> " + userSkillLevel.isChecked());
				Skill skill = null;

				Optional<Skill> optionalSkill = skillRepository.findById(userSkillLevel.getId());
				System.out.println("ML-2: ---> " + optionalSkill.isPresent());
				if (optionalSkill.isPresent()) {
					skill = optionalSkill.get();
					//optionalSkill.isPresent()
					System.out.println("ML-3: ---> " + optionalSkill.get());
				}

				//

				if (!(userSkills.contains(skill))) {
					user.addSkill(skill);
				}
			} else {
				Skill skill = null;

				Optional<Skill> optionalSkill = skillRepository.findById(userSkillLevel.getId());
				if (optionalSkill.isPresent()) {
					skill = optionalSkill.get();
					System.out.println("ML-4: ---> " + optionalSkill.get());
				}
				if (userSkills.contains(skill)) {
					user.removeSkill(skill, userSkillRepository);
				}
			}
		}

		// anzupassen
		System.out.println("------------>aus UserForm (z.B. UserName: " + userForm.getUserName());
		System.out.println(userForm.getUserSkillLevels());
		user.setUserName(userForm.getUserName());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setZipCode(userForm.getZipCode());
		user.setCity(userForm.getCity());
		user.setDateOfBirth(userForm.getDateOfBirth());
		user.setEmail(userForm.getEmail());
		user.setDescription(userForm.getDescription());
		user.setImageURL(userForm.getImageURL());

		userRepository.save(user);

		System.out.println("==============================================================================");
		for (int i = 0; i < userSkills.size(); i++) {
			System.out.println("alter User Skill(s): " + user.getUserSkills().get(i).getSkill().getName());
		}
		System.out.println("==============================================================================");

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

		return "redirect:/user/deleted";
	}

	// Delete a user
	@GetMapping("/user/deleted")
	public String deletedUser() {

		return "/user/deleted";
	}

}
