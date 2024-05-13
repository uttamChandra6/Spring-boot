package com.testSpringProj.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class userDetails {
	
	@Id
	@GeneratedValue
	private Integer ID;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public userDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public userDetails(String name) {
		super();
		this.name = name;
	}

}
