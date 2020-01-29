package com.wildcodeschool.skillhub.model;

public class UserSkillLevel {
	boolean hasSkill;
	String skillName;
	
	public UserSkillLevel(boolean hasSkill, String name) {
		super();
		this.hasSkill = hasSkill;
		this.skillName = name;
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

	public void setSkillName(String name) {
		this.skillName = name;
	}

}
