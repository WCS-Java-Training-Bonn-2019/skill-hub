package com.wildcodeschool.skillhub.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@Column(name = ("id"), updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	private Long id;
	private String userName;
	private String imageURL;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String zipCode;
	private String city;
	private String email;
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserSkill> skills = new ArrayList<>();

	@SuppressWarnings("unused")
	public User() {
	}

	public User(String userName, String imageURL, String firstName, String lastName, LocalDate dateOfBirth,
			String zipCode, String city, String email, String description) {
		super();
		this.userName = userName;
		this.imageURL = imageURL;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.zipCode = zipCode;
		this.city = city;
		this.email = email;
		this.description = description;
	}

	public Long getId() {
		return id;
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

	public void setDateOfBirth(LocalDate datedateOfBirth) {
		this.dateOfBirth = datedateOfBirth;
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

	public void addSkill(Skill skill) {
		UserSkill userSkill = new UserSkill(this, skill,  new Date(), true);

		// Add UserSkill to List in User
		skills.add(userSkill);

		// Add UserSkill to List in Skill
		skill.getUsers().add(userSkill);
	}

	public void removeSkill(Skill skill) {
//
//		// Iterate over all UserSkills of the User
//		for (Iterator<UserSkill> iterator = skills.iterator(); iterator.hasNext();) {
//			UserSkill userSkill = iterator.next();
//
//			// If UserSkill matches this User and the Skill to be removed
//			if (userSkill.getUser().equals(this) && userSkill.getSkill().equals(skill)) {
//
//				// Remove UserSkill from List in User
//				iterator.remove();
//
//				// Remove UserSkill from List in Skill
//				userSkill.getSkill().getUsers().remove(userSkill);
//
//				// null the UserSkill
//				userSkill.setUser(null);
//				userSkill.setSkill(null);
//			}
//		}
	}

	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getUserName()=" + getUserName() + ", getImageURL()=" + getImageURL()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getDatedateOfBirth()="
				+ getDateOfBirth() + ", getZipCode()=" + getZipCode() + ", getCity()=" + getCity() + ", getEmail()="
				+ getEmail() + ", getDescription()=" + getDescription() + ", getAge()=" + getAge() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, city, dateOfBirth, description, email, firstName, imageURL, lastName,
				zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(userName, other.userName) && Objects.equals(city, other.city)
				&& Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(description, other.description) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(imageURL, other.imageURL)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(zipCode, other.zipCode);
	}

}
