package com.wildcodeschool.skillhub.service;

import java.util.List;
import java.util.Optional;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;

public interface UserService {

	List<User> getUsers();

	List<User> getUsersWithSkill(Skill skill);

	Optional<User> getSingleUserById(Long userId);

	Optional<User> getSingleUserByEmail(String email);

	User createNewUser(User user);

	void updateUser(User user);

	void deleteUser(Long userId);

	boolean emailExists(String email);

}