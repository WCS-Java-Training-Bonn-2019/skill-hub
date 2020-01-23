package com.wildcodeschool.skillhub.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	//private SkillRepository skillRepository;
	

	@GetMapping("/users/search")
	public String getBySkill(Model model, @RequestParam Long id) {

		// Handover skill to use attibutes in HTML
		//model.addAttribute("skill", 
		//		skillRepository.findById(id));
		model.addAttribute("users", 
				userRepository.findBySkills_SkillId(id));
		
//		List<Skill> skills = new ArrayList<>();
//
//		skills = skillRepository.findByName(skillName);
//
//		for (Skill skill : skills) {
//			model.addAttribute("users", userRepository.findBySkills_SkillId(skill.getId()));
//		}

		return "users";
	}
	
	
@GetMapping("/users/userslist")
    public String getAll(Model model) {

        model.addAttribute("see_created_users", userRepository.findAll());

        return "see_created_users";
    }

	
	@GetMapping("/users/create")
    public String getUser(Model model,
                            @RequestParam(required = false) Long id) {

        User user = new User();
        if (id != null) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        model.addAttribute("user", user);

        return "create_user";
    }
    
    
    @PostMapping("/users/create")
    public String postUser(@ModelAttribute User user) {

    	userRepository.save(user);
        return "redirect:/users/create";
    }
    
    /*
    @GetMapping("/users/create/delete")
    public String deleteUser(@RequestParam Long id) {

    	userRepository.deleteById(id);

        return "redirect:/users/create";
    }
    */
    
    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam Long id) {

    	userRepository.deleteById(id);

        return "redirect:/see_created_users";
    }
    
    
    

}
