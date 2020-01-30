package com.wildcodeschool.skillhub.form;

public class UserSkillLevel {
	Long id;
	String name;
	boolean checked;
	String imageURL;
	
	public UserSkillLevel(Long id, String name, boolean checked, String imageURL) {
		super();
		this.id = id;
		this.name = name;
		this.checked = checked;
		this.imageURL = imageURL;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
