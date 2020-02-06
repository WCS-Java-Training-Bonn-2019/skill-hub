package com.wildcodeschool.skillhub.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_skill" )
@Getter
@Setter
@ToString
public class UserSkill {

	@EmbeddedId
	private UserSkillId id;

	@ManyToOne
	@MapsId("userId")
	private User user;

	@ManyToOne
	@MapsId("skillId")
	private Skill skill;

	private Date createdOn;
	private Boolean isOfferingSkill;

	public UserSkill() {
	}

	public UserSkill(User user, Skill skill, Date createdOn, Boolean isOfferingSkill) {
		super();
		this.id = new UserSkillId(user.getId(), skill.getId());
		this.user = user;
		this.skill = skill;
		this.createdOn = createdOn;
		this.isOfferingSkill = isOfferingSkill;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSkill other = (UserSkill) obj;
		return Objects.equals(id, other.id);
	}

}
