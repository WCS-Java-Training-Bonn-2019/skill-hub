package com.wildcodeschool.skillhub.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class UserSkill {

	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Id
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@PrimaryKeyJoinColumn(name = "skill_id", referencedColumnName = "id")
	private Skill skill;

	@Override
	public int hashCode() {
		return Objects.hash(skill, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof UserSkill))
			return false;
		UserSkill other = (UserSkill) obj;
		return Objects.equals(skill, other.skill) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "UserSkill [user=" + user + ", skill=" + skill + "]";
	}

}
