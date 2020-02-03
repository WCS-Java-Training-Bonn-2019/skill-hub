package com.wildcodeschool.skillhub.service;

import java.util.List;
import java.util.Optional;

import com.wildcodeschool.skillhub.model.Skill;

public interface SkillService {

	Optional<Skill> findById(Long id);

	List<Skill> findAll();

	void save(Skill skill);

}