package com.wildcodeschool.skillhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.skillhub.model.User;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/users/search")
	public String getBySkill(Model model, @RequestParam String skillName){
		
		
		repository.findBySkillName(skillName);
		model.addAttribute("users", repository.findBySkillName(skillName));
		
		return "users";
	}
	
	

}
