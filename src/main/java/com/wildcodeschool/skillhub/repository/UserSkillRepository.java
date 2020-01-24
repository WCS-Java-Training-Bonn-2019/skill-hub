package com.wildcodeschool.skillhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.skillhub.model.UserSkill;
import com.wildcodeschool.skillhub.model.UserSkillId;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillId>{

}
