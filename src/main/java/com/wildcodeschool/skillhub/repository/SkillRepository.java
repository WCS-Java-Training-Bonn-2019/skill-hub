package com.wildcodeschool.skillhub.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.wildcodeschool.skillhub.model.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {

	List<Skill> findAll();
	List<Skill> findByName(String name);

}
