package com.wildcodeschool.skillhub.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "user_skill")
public class UserSkill {

	@EmbeddedId
	private UserSkillId id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId("user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "skill_id")
	@MapsId("skill_id")
	private Skill skill;

	private Date createdOn;
	private Boolean isOfferingSkill;

	@SuppressWarnings("unused")
	private UserSkill() {
	}

	public UserSkill(User user, Skill skill, Date createdOn, Boolean isOfferingSkill) {
		super();
		this.id = new UserSkillId(user.getId(), skill.getId());
		this.user = user;
		this.skill = skill;
		this.createdOn = createdOn;
		this.isOfferingSkill = isOfferingSkill;
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Boolean getIsOfferingSkill() {
		return isOfferingSkill;
	}

	public void setIsOfferingSkill(Boolean isOfferingSkill) {
		this.isOfferingSkill = isOfferingSkill;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, isOfferingSkill);
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
		return Objects.equals(id, other.id) && Objects.equals(isOfferingSkill, other.isOfferingSkill);
	}

}
