package com.wildcodeschool.skillhub.service;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.model.UserSkill;
import com.wildcodeschool.skillhub.model.UserSkillId;
import com.wildcodeschool.skillhub.repository.UserSkillRepository;

@Service
public class UserSkillServiceImpl implements UserSkillService {

	@Autowired
	private UserSkillRepository userSkillRepository;

	@Override
	public void addUserSkill(User user, Skill skill) {
		UserSkill userSkill = new UserSkill(user, skill, new Date(), true);

		// Add UserSkill to List in User
		user.getUserSkills().add(userSkill);

		// Add UserSkill to List in Skill
		skill.getUserSkills().add(userSkill);

		// Add UserSkill to repository
		userSkillRepository.save(userSkill);
	}

	@Override
	public void removeUserSkill(User user, Skill skill) {

		// Iterate over all UserSkills of the User
		for (Iterator<UserSkill> iterator = user.getUserSkills().iterator(); iterator.hasNext();) {
			UserSkill userSkill = iterator.next();

			// If UserSkill matches this User and the Skill to be removed
			if (userSkill.getUser().equals(user) && userSkill.getSkill().equals(skill)) {

				// Remove UserSkill from List in User
				iterator.remove();

				// Remove UserSkill from List in Skill
				userSkill.getSkill().getUserSkills().remove(userSkill);

				// Remove UserSkill from repository
				userSkillRepository.deleteById(new UserSkillId(user.getId(), skill.getId()));
			}
		}
	}

}
