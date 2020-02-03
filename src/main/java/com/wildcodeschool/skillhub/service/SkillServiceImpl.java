package com.wildcodeschool.skillhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public Optional<Skill> findById(Long id) {
		return skillRepository.findById(id);
	}

	@Override
	public List<Skill> findAll() {
		return skillRepository.findAll();
	}

	@Override
	public void save(Skill skill) {
		skillRepository.save(skill);
	}

}
