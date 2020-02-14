package com.wildcodeschool.skillhub.form;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.wildcodeschool.skillhub.model.User;

public class UserForm {

	private Long id;
	//private String imageURL;
	private byte[] image;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String zipCode;
	private String city;
	private String email;
	private String password;
	private String repeatedPassword;
	private String description;

	private List<UserSkillLevel> userSkillLevels = new ArrayList<>();

	public UserForm() {
	}

	public void setUser(User user) {
		//this.imageURL = user.getImageURL();
		this.image = user.getImage();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.dateOfBirth = user.getDateOfBirth();
		this.zipCode = user.getZipCode();
		this.city = user.getCity();
		this.email = user.getEmail();
		this.description = user.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//New Getter and Setter for image
	
	public byte[] getImage() {
	return image;
}

public void setImage(byte[] image) {
	this.image = image;
}
	
//	public String getImageURL() {
//		return imageURL;
//	}
//
//	public void setImageURL(String imageURL) {
//		this.imageURL = imageURL;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeated() {
		return repeatedPassword;
	}

	public void setPasswordRepeated(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAge() {
		return Period.between(getDateOfBirth(), LocalDate.now()).getYears();
	}

	public List<UserSkillLevel> getUserSkillLevels() {
		return userSkillLevels;
	}

	public void setUserSkillLevels(List<UserSkillLevel> userSkillLevel) {
		this.userSkillLevels = userSkillLevel;
	}

}
