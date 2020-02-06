package com.wildcodeschool.skillhub.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Skill {

	@Id
	@Column(name = ("id"), updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_generator")
	private Long id;
	private String name;
	private String imageURL;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "skill")
	private List<UserSkill> userSkills = new ArrayList<>();

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

}
