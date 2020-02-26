package com.wildcodeschool.skillhub.service;

import java.util.List;
import java.util.Optional;

import com.wildcodeschool.skillhub.model.Skill;

public interface SkillService {

	List<Skill> getAllSkills();

	Optional<Skill> getSingleSkillById(Long skillId);

	Skill createNewSkill(Skill skill);

}