package com.wildcodeschool.skillhub.model;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

@Entity
@Table(name = "user")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NaturalId
	private String alias;
	private URL imageURL;
	private String firstName;
	private String lastName;
	private LocalDate datedateOfBirth;
	private String zipCode;
	private String city;
	private String email;
	private String description;

	@OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserSkill> skills = new ArrayList<>();

	@SuppressWarnings("unused")
	private User() {
	}

	public User(String alias, URL imageURL, String firstName, String lastName, LocalDate datedateOfBirth,
			String zipCode, String city, String email, String description) {
		super();
		this.alias = alias;
		this.imageURL = imageURL;
		this.firstName = firstName;
		this.lastName = lastName;
		this.datedateOfBirth = datedateOfBirth;
		this.zipCode = zipCode;
		this.city = city;
		this.email = email;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public URL getImageURL() {
		return imageURL;
	}

	public void setImageURL(URL imageURL) {
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

	public LocalDate getDatedateOfBirth() {
		return datedateOfBirth;
	}

	public void setDatedateOfBirth(LocalDate datedateOfBirth) {
		this.datedateOfBirth = datedateOfBirth;
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

	public void addSkill(Skill skill) {
		UserSkill userSkill = new UserSkill(this, skill);

		// Add UserSkill to List in User
		skills.add(userSkill);

		// Add UserSkill to List in Skill
		skill.getUsers().add(userSkill);
	}

	public void removeSkill(Skill skill) {

		// Iterate over all UserSkills of the User
		for (Iterator<UserSkill> iterator = skills.iterator(); iterator.hasNext();) {
			UserSkill userSkill = iterator.next();

			// If UserSkill matches this User and the Skill to be removed
			if (userSkill.getUser().equals(this) && userSkill.getSkill().equals(skill)) {

				// Remove UserSkill from List in User
				iterator.remove();

				// Remove UserSkill from List in Skill
				userSkill.getSkill().getUsers().remove(userSkill);

				// null the UserSkill
				userSkill.setUser(null);
				userSkill.setSkill(null);
			}
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, city, datedateOfBirth, description, email, firstName, imageURL, lastName, zipCode);
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
		return Objects.equals(alias, other.alias) && Objects.equals(city, other.city)
				&& Objects.equals(datedateOfBirth, other.datedateOfBirth)
				&& Objects.equals(description, other.description) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(imageURL, other.imageURL)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(zipCode, other.zipCode);
	}

}
