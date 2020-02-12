package com.wildcodeschool.skillhub.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//IMPORTANT: Do NOT use lombok @Data, @EqualsAndHashCode or @ToString
@Entity
@Table(name = "user_skill")
@IdClass(UserSkillId.class)
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class UserSkill {

	@Id
	private Long userId;

	@Id
	private Long skillId;

	@ManyToOne
//	@PrimaryKeyJoinColumn(name="user_id", referencedColumnName="id")
	@JoinColumn(name = "user_id", updatable = false, insertable = false)
	private User user;

	@ManyToOne
//	@PrimaryKeyJoinColumn(name="skill_id", referencedColumnName="id")
	@JoinColumn(name = "skill_id", updatable = false, insertable = false)
	private Skill skill;

}
