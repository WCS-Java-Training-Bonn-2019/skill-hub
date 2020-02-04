package com.wildcodeschool.skillhub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

	private final SkillRepository skillRepository;

	@Autowired
	public SkillServiceImpl(SkillRepository skillRepository) {
		super();
		this.skillRepository = skillRepository;
	}

	@Override
	public Skill getSingleSkill(Long skillId) {
		// TODO Add checks
		return skillRepository.getOne(skillId);
	}

	@Override
	public List<Skill> getSkills() {
		return skillRepository.findAll();
	}

	@Override
	public void createNewSkill(Skill skill) {
		// TODO Check if skill exists
		skillRepository.save(skill);
	}

}
