package com.wildcodeschool.skillhub.service;

import java.util.List;

import com.wildcodeschool.skillhub.model.Skill;

public interface SkillService {

	Skill getSingleSkill(Long skillId);

	List<Skill> getSkills();

	void createNewSkill(Skill skill);

}