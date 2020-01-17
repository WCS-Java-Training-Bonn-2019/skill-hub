package com.wildcodeschool.skillhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.skillhub.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users/search")
	public String getBySkill(Model model, @RequestParam String skillName) {

		// TODO Remove mock implementation
		model.addAttribute("users", userRepository.findAll());
		
//		List<Skill> skills = new ArrayList<>();
//
//		skills = skillRepository.findByName(skillName);
//
//		for (Skill skill : skills) {
//			model.addAttribute("users", userRepository.findBySkills_SkillId(skill.getId()));
//		}

		return "users";
	}

}
