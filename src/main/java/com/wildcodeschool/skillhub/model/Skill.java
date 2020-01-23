package com.wildcodeschool.skillhub.model;

import java.util.ArrayList;
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
public class Skill {

	@Id
	@Column(name = ("id"), updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_generator")
	private Long id;
	private String name;
	private String imageURL;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "skill", cascade = CascadeType.ALL)
	private List<UserSkill> users = new ArrayList<>();

	@SuppressWarnings("unused")
	private Skill() {
	}

	public Skill(String name, String imageURL) {
		super();
		this.name = name;
		this.imageURL = imageURL;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public List<UserSkill> getUsers() {
		return users;
	}

	@Override
	public String toString() {
		return "Skill [getId=" + getId() + ", getName()=" + getName() + ", getImageURL()=" + getImageURL() + "]";

	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
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
		return Objects.equals(name, other.name);
	}

}
