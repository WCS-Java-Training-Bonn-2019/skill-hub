package com.wildcodeschool.skillhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.skillhub.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();
	List<User> findByuserSkills_SkillId(Long id);
	Optional<User> findById(Long id);

}
