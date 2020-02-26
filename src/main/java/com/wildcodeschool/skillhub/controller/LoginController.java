package com.wildcodeschool.skillhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLoginPage() {

		return "login";
	}

	@GetMapping("/login_ml")
	public String getLoginPage2() {

		return "login_ml";
	}

	@GetMapping("/logout")
	public String getLogout() {

		return "logout";
	}

}