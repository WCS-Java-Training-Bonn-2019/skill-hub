package com.wildcodeschool.skillhub.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*

@Controller
public class LoginController {
	
	
	@EnableWebMvc
	@ComponentScan("org.springframework.security.samples.mvc")
	public class WebMvcConfiguration implements WebMvcConfigurer {

	    // ...

	    @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/login").setViewName("login");
	        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    }
	}

}


// Link...
// https://docs.spring.io/spring-security/site/docs/current/guides/html5/form-javaconfig.html



/*  --> delete...


@Controller
public class SkillController {

	@Autowired
	private SkillRepository skillRepository;

	@GetMapping("/skills")
	public String getAllSkills(Model model) {
		model.addAttribute("skills", skillRepository.findAll());
		return "skills";
	}
}

*/









