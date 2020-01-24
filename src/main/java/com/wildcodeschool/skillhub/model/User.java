package com.wildcodeschool.skillhub.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.wildcodeschool.skillhub.repository.UserSkillRepository;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String zipCode;
	private String city;
	private String email;
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserSkill> skills = new ArrayList<>();

	@SuppressWarnings("unused")
	public User() {
	}

	public User(String userName, String imageURL, String firstName, String lastName, LocalDate dateOfBirth, String zipCode,
			String city, String email, String description) {
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
		
		/*
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateToday = new Date();
		Calendar a = getCalendar(dateOfBirth);
		Calendar b = getCalendar(dateToday);
		int diff = b.get(YEAR) - a.get(YEAR);
		if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
			diff--;
		}
		return diff;
}
	// return Period.between(getDateOfBirth(), LocalDate.now()).getYears();
	

	// =======================================================================

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.GERMANY);
		cal.setTime(date);
		return cal;
	}
	// =======================================================================
*/


	public void addSkill(Skill skill) {
		UserSkill userSkill = new UserSkill(this, skill, new Date(), true);

		// Add UserSkill to List in User
		skills.add(userSkill);

		// Add UserSkill to List in Skill
		skill.getUsers().add(userSkill);
	}

//  TODO Make this work!	
//	public void removeSkill(Skill skill) {
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
//			}
//		}
//	}

	public void removeSkill(Skill skill, UserSkillRepository userSkillRepository) {

		// Iterate over all UserSkills of the User
		for (Iterator<UserSkill> iterator = skills.iterator(); iterator.hasNext();) {
			UserSkill userSkill = iterator.next();

			// If UserSkill matches this User and the Skill to be removed
			if (userSkill.getUser().equals(this) && userSkill.getSkill().equals(skill)) {

				// Remove UserSkill
				userSkillRepository.deleteById(new UserSkillId(this.getId(), skill.getId()));

				// Remove UserSkill from List in User
				iterator.remove();

				// Remove UserSkill from List in Skill
				userSkill.getSkill().getUsers().remove(userSkill);

			}
		}
	}
	
	
	
	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getUserName()=" + getUserName() + ", getImageURL()=" + getImageURL()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getDateOfBirth()="
				+ getDateOfBirth() + ", getZipCode()=" + getZipCode() + ", getCity()=" + getCity() + ", getEmail()="
				+ getEmail() + ", getDescription()=" + getDescription() + ", getAge()=" + getAge() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, city, dateOfBirth, description, email, firstName, imageURL, lastName, zipCode);
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
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(description, other.description)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(imageURL, other.imageURL) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(zipCode, other.zipCode);
	}

	public List<UserSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<UserSkill> skills) {
		this.skills = skills;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
