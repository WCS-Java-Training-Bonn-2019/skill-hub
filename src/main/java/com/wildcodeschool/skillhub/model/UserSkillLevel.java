package com.wildcodeschool.skillhub.model;

public class UserSkillLevel {
	boolean hasSkill;
	String skillName;
	Long skillId;
	
	public UserSkillLevel(boolean hasSkill, String skillName, Long skillId) {
		super();
		this.hasSkill = hasSkill;
		this.skillName = skillName;
		this.skillId = skillId;
	}

	public boolean isHasSkill() {
		return hasSkill;
	}

	public void setHasSkill(boolean hasSkill) {
		this.hasSkill = hasSkill;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}


}
