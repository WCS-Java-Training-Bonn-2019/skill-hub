package com.wildcodeschool.skillhub.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.wildcodeschool.skillhub.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findAll();
	List<User> findBySkills_SkillId(Long id);

}
