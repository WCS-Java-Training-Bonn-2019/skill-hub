package com.wildcodeschool.skillhub.service;

import java.util.List;
import java.util.Optional;

import com.wildcodeschool.skillhub.model.User;

public interface UserService {

	Optional<User> getSingleUser(Long userId);

	List<User> getUsersBySkillId(Long skillid);

	List<User> getUsers();

	void deleteUser(Long userId);

	void createNewUser(User user);
	
	void updateUser(User user);

}