package com.wildcodeschool.skillhub.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.model.UserSkill;
import com.wildcodeschool.skillhub.repository.SkillRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final SkillRepository skillRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, SkillRepository skillRepository) {
		super();
		this.userRepository = userRepository;
		this.skillRepository = skillRepository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	
	@Override
	public List<User> findAllUsersOrderByFirstName() {
		//return userRepository.findAll();
		return userRepository.findAllByOrderByFirstName();
	}
	

	@Override
	public List<User> getUsersWithSkill(Skill skill) {
		return userRepository.findByUserSkills_Skill(skill);
	}

	@Override
	public Optional<User> getSingleUserById(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public Optional<User> getSingleUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public User createNewUser(User user) {
		// Check if a new user has been passed i.e. id of user is null
		if (user.getId() != null) {

			// TODO Find a better exception
			throw new IllegalArgumentException();
		}

		replaceDetachedSkillsByPersistedSkills(user);

		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		// Check if an existing user has been passed
		if (userRepository.findById(user.getId()).isEmpty()) {

			// TODO Find a better exception
			throw new IllegalArgumentException();
		}

		replaceDetachedSkillsByPersistedSkills(user);

		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userRepository.deleteById(user.getId());
	}

	@Override
	public boolean emailExists(String email) {

		Optional<User> optionalUser = userRepository.findByEmail(email);

		return optionalUser.isPresent();
	}

	private void replaceDetachedSkillsByPersistedSkills(User user) {
		Collection<UserSkill> userSkills = user.getUserSkills();

		for (UserSkill userSkill : userSkills) {
			Optional<Skill> optionalPersistedSkill = skillRepository.findById(userSkill.getSkill().getId());

			if (optionalPersistedSkill.isPresent()) {
				userSkill.setSkill(optionalPersistedSkill.get());
			} else {
				userSkills.remove(userSkill);
			}
		}
	}

}
