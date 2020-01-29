package com.wildcodeschool.skillhub.model;

public class UserSkillLevel {
	boolean hasSkill;
	int userSkillLevelInt; 
	
	// constructor	
	public UserSkillLevel() {
		super();
		this.hasSkill = false;
		this.userSkillLevelInt = 1; // 1: basic / 2: advanced / 3: professional
		
	
	// getter & setter	
		
		
	}

	public boolean isHasSkill() {
		return hasSkill;
	}

	public void setHasSkill(boolean hasSkill) {
		this.hasSkill = hasSkill;
	}

	public int getUserSkillLevelInt() {
		return userSkillLevelInt;
	}

	public void setUserSkillLevelInt(int userSkillLevelInt) {
		this.userSkillLevelInt = userSkillLevelInt;
	}
}
