package com.wildcodeschool.skillhub.form;

public class UserSkillLevel {
	boolean skillChecked;
	String skillName;
	Long skillId;
	
	public UserSkillLevel(boolean hasSkill, String skillName, Long skillId) {
		super();
		this.skillChecked = hasSkill;
		this.skillName = skillName;
		this.skillId = skillId;
	}

	public boolean getSkillChecked() {
		return skillChecked;
	}

	public void setSkillChecked(boolean hasSkill) {
		this.skillChecked = hasSkill;
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
