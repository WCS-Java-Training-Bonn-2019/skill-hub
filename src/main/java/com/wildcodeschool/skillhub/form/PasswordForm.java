package com.wildcodeschool.skillhub.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wildcodeschool.skillhub.model.User;

public class PasswordForm {
	
	private Long id;
	//private String email;
	private String password;
	private String repeatedPassword;
	
	public PasswordForm() {
	}
	
	public void setPassword(User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password = passwordEncoder.encode(password);
		this.repeatedPassword = passwordEncoder.encode(repeatedPassword);
	}

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeated() {
		return repeatedPassword;
	}

	public void setPasswordRepeated(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
}

