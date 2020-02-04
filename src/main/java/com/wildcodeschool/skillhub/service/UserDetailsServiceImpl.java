package com.wildcodeschool.skillhub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.model.AdminUserDetails;
import com.wildcodeschool.skillhub.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository repository;
	private final String adminPassword;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository repository, @Value("${admin.password}") String adminPassword) {
		this.repository = repository;
		this.adminPassword = adminPassword;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("admin".equals(username)) {
			return getAdminUserDetails();
		}
		return loadUserByName(username);
	}

	private UserDetails getAdminUserDetails() {
		return new AdminUserDetails("admin", adminPassword);
	}

	private UserDetails loadUserByName(String email) {
		Optional<User> optionalUser = repository.findByEmail(email);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		throw new UsernameNotFoundException("User '" + email + "' not found.");
	}
}
