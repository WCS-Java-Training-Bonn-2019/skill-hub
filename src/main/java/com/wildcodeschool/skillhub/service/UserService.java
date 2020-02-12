package com.wildcodeschool.skillhub.service;

import java.util.List;
import java.util.Optional;

import com.wildcodeschool.skillhub.model.User;

public interface UserService {

	Optional<User> getSingleUser(Long userId);

	Optional<User> getSingleUserByEmail(String email);

	List<User> getUsers();

	void deleteUser(Long userId);

	User createNewUser(User user);

	void updateUser(User user);

	boolean emailExists(String email);

}