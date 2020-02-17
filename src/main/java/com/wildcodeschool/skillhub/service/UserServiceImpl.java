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
	public List<User> getUsersWithSkill(Skill skill) {
		return userRepository.findByUserSkills_Skill(skill);
	}

	@Override
	public Optional<User> getSingleUserById(Long userId) {
		// TODO Add checks
		return userRepository.findById(userId);
	}

	@Override
	public Optional<User> getSingleUserByEmail(String email) {
		// TODO Add checks
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public User createNewUser(User user) {
		// Check if a new user has been passed i.e. id of user is null
		if (user.getId() != null) {
			throw new IllegalArgumentException();
		}

		Collection<UserSkill> userSkills = user.getUserSkills();

		for (UserSkill userSkill : userSkills) {
			Optional<Skill> optionalPersistedSkill = skillRepository.findById(userSkill.getSkill().getId());

			// Retrieve all skills from repository to get them in managed state for this
			// transaction
			if (optionalPersistedSkill.isPresent()) {
				userSkill.setSkill(optionalPersistedSkill.get());
			} else {
				userSkills.remove(userSkill);
			}
		}

		return userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Add checks etc.
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Add checks
		userRepository.deleteById(userId);
	}

	@Override
	public boolean emailExists(String email) {

		Optional<User> optionalUser = userRepository.findByEmail(email);

		return optionalUser.isPresent();
	}

}
