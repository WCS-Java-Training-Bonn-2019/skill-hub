package com.wildcodeschool.skillhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public List<User> findByuserSkills_SkillId(Long id) {
		return userRepository.findByuserSkills_SkillId(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public void save(User user) {
		userRepository.save(user);
	}

}
