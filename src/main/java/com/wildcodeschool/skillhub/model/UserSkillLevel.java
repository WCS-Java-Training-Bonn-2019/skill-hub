package com.wildcodeschool.skillhub.model;

public class UserSkillLevel {
	boolean hasSkill;
	String name;
	
	public UserSkillLevel(boolean hasSkill, String name) {
		super();
		this.hasSkill = hasSkill;
		this.name = name;
	}

	public boolean isHasSkill() {
		return hasSkill;
	}

	public void setHasSkill(boolean hasSkill) {
		this.hasSkill = hasSkill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
