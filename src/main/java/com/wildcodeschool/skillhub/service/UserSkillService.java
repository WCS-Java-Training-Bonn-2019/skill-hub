package com.wildcodeschool.skillhub.service;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;

public interface UserSkillService {

	void addNewUserSkill(User user, Skill skill);

	void removeUserSkill(User user, Skill skill);

}