package com.wildcodeschool.skillhub.service;

import java.util.List;
import java.util.Optional;

import com.wildcodeschool.skillhub.model.User;

public interface UserService {

	Optional<User> findById(Long id);

	List<User> findByuserSkills_SkillId(Long id);

	List<User> findAll();

	void deleteById(Long id);

	void save(User user);

}