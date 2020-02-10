package com.wildcodeschool.skillhub.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Skill implements Comparable<Skill> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String imageURL;

	@OneToMany(mappedBy = "skill")
	private Set<UserSkill> userSkills = new HashSet<>();

	public Skill() {
	}

	public Skill(String name, String imageURL) {
		super();
		this.name = name;
		this.imageURL = imageURL;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int compareTo(Skill o) {
		return this.getName().compareTo(o.getName());
	}

}
