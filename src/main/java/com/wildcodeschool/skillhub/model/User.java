package com.wildcodeschool.skillhub.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//IMPORTANT: Do NOT use lombok @Data, @EqualsAndHashCode or @ToString
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5641612720821997134L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String email;

	@NotNull
	private String password;

	@NotNull
	private String firstName;
	private String lastName;

	private String zipCode;

	@NotNull
	private String city;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate dateOfBirth;

	private String description;
	private String imageURL;

	@OneToMany(mappedBy = "user")
	@Builder.Default
	private Set<UserSkill> userSkills = new HashSet<>();

	public int getAge() {
		return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
	}

	// TODO Remove the need for this method
	public Set<Long> getUserSkillIds() {
		Set<Long> userSkillIds = new HashSet<>();
		this.getUserSkills().iterator().forEachRemaining(userSkill -> userSkillIds.add(userSkill.getSkill().getId()));
		return userSkillIds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", zipCode=" + zipCode + ", city=" + city + ", dateOfBirth=" + dateOfBirth
				+ ", description=" + description + ", imageURL=" + imageURL + "]";
	}

	// Methods required by UserDetails interface
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		return Collections.singletonList(authority);
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
