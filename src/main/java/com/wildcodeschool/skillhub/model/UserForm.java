package com.wildcodeschool.skillhub.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;

//@Entity
public class UserForm {

//	@Id
//	@Column(name = ("id"), updatable = false, nullable = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	private Long id;
	private String userName;
	private String imageURL;
	private String firstName;
	private String lastName;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String zipCode;
	private String city;
	private String email;
	private String description;

	
	private List<UserSkillLevel> userSkillLevel = new ArrayList<>();
	
	public UserForm() {
	}

	public UserForm(User user) {
		super();
		this.userName = user.getUserName();
		this.imageURL = user.getImageURL();
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAge() {
		return Period.between(getDateOfBirth(), LocalDate.now()).getYears();
	}

	public List<UserSkillLevel> getUserSkillLevel() {
		return userSkillLevel;
	}

	public void setUserSkillLevel(List<UserSkillLevel> userSkillLevel) {
		this.userSkillLevel = userSkillLevel;
	}

}
