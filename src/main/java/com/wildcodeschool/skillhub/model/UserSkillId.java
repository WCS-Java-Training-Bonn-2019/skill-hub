package com.wildcodeschool.skillhub.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//IMPORTANT: Do NOT use lombok @Data, @EqualsAndHashCode or @ToString
@NoArgsConstructor
@Setter
@Getter
public class UserSkillId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -715344968158529197L;

	private User user;
	private Skill skill;

	@Override
	public int hashCode() {
		return Objects.hash(skill, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof UserSkillId))
			return false;
		UserSkillId other = (UserSkillId) obj;
		return Objects.equals(skill, other.skill) && Objects.equals(user, other.user);
	}

}
