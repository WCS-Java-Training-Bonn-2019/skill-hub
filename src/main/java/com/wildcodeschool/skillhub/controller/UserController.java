package com.wildcodeschool.skillhub.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wildcodeschool.skillhub.repository.SkillRepository;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	private SkillRepository skillRepository;
	

	@GetMapping("/users/search")
	public String getBySkill(Model model, @RequestParam Long id) {

		// TODO Remove mock implementation
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
	
	
//====================================================
/*	
@GetMapping("/wizards")
    public String getAll(Model model) {

        model.addAttribute("wizards", repository.findAll());

        return "wizards";
    }
*/	
//====================================================	
	
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

        return "users/create";
    }
    
    
    @PostMapping("/users/create")
    public String postWizard(@ModelAttribute User user) {

    	userRepository.save(user);
        return "redirect:/users/create";
    }
    
    
    @GetMapping("/users/create/delete")
    public String deleteUser(@RequestParam Long id) {

    	userRepository.deleteById(id);

        return "redirect:/users/create";
    }

}
<<<<<<< HEAD

=======
	
	
	
	
	
>>>>>>> 356202e1aa1f692ea02455fb417e0a62e6d61aec
