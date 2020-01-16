package com.wildcodeschool.skillhub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.repository.SkillRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SkillRepository skillRepository;

	@GetMapping("/users/search")
	public String getBySkill(Model model, @RequestParam String skillName) {

		List<Skill> skills = new ArrayList<>();

		skills = skillRepository.findByName(skillName);

		for (Skill skill : skills) {
			model.addAttribute("users", userRepository.findBySkills_SkillId(skill.getId()));
		}

		return "users";
	}

}
