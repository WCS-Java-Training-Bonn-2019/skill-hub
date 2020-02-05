package com.wildcodeschool.skillhub.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import static java.util.Collections.singletonList;
import java.util.Collection;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = ("id"), updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	private Long id;
	private String imageURL;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String zipCode;
	private String city;
	private String email;
	private String password;
	private String description;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "user")
	private List<UserSkill> userSkills = new ArrayList<>();

	public User() {
	}

	public User(String imageURL, String firstName, String lastName, LocalDate dateOfBirth, String zipCode, String city,
			String email, String password, String description) {
		super();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.imageURL = imageURL;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.zipCode = zipCode;
		this.city = city;
		this.email = email;
		this.password = passwordEncoder.encode(password);
		this.description = description;
	}

	public Long getId() {
		return id;
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
	

	public void setPassword(String password) {
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

	public List<Long> getUserSkillIds() {
		List<Long> userSkillIds = new ArrayList<>();
		this.getUserSkills().iterator().forEachRemaining(userSkill -> userSkillIds.add(userSkill.getSkill().getId()));
		return userSkillIds;
	}

	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getImageURL()=" + getImageURL() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getDateOfBirth()=" + getDateOfBirth() + ", getZipCode()="
				+ getZipCode() + ", getCity()=" + getCity() + ", getEmail()=" + getEmail() + ", getDescription()="
				+ getDescription() + ", getAge()=" + getAge() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, dateOfBirth, description, email, firstName, imageURL, lastName, zipCode);
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
		return Objects.equals(city, other.city) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(description, other.description) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(imageURL, other.imageURL)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(zipCode, other.zipCode);
	}

	public List<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	// Methods from User Details Interface
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CUSTOMER");
		return singletonList(authority);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
