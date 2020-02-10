package com.wildcodeschool.skillhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangePwController {
	
	@GetMapping("/changePw")
	public String getLoginPage() {

		return "changePw";
	}
	
}


