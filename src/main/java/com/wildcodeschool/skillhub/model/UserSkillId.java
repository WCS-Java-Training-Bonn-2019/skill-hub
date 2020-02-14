package com.wildcodeschool.skillhub.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class UserSkillId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1848599333691281404L;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "skill_id")
	private Long skillId;

	public UserSkillId() {
	}

	public UserSkillId(Long userId, Long skillId) {
		super();
		this.userId = userId;
		this.skillId = skillId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(skillId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSkillId other = (UserSkillId) obj;
		return Objects.equals(skillId, other.skillId) && Objects.equals(userId, other.userId);
	}

}
