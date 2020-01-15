package com.wildcodeschool.skillhub.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "user_skill")
public class UserSkill {

	@EmbeddedId
	private UserSkillId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("skillId")
	private Skill skill;

	@Column(name = "created_on")
	private Date createdOn = new Date();

	@Column(name = "is_offering_skill")
	private Boolean isOfferingSkill;

	@SuppressWarnings("unused")
	private UserSkill() {
	}

	public UserSkill(User user, Skill skill) {
		this.user = user;
		this.skill = skill;
		this.id = new UserSkillId(user.getId(), skill.getId());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Boolean getIsOfferingSkill() {
		return isOfferingSkill;
	}

	public void setIsOfferingSkill(Boolean isOfferingSkill) {
		this.isOfferingSkill = isOfferingSkill;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdOn, isOfferingSkill, skill, user);
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
		return Objects.equals(createdOn, other.createdOn) && Objects.equals(isOfferingSkill, other.isOfferingSkill)
				&& Objects.equals(skill, other.skill) && Objects.equals(user, other.user);
	}

}
