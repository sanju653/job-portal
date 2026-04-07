package com.spring.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Company {
	@Id
	@GeneratedValue
private Long id;
private String name;
@Column(unique = true, nullable = false)
private String email;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Company(Long id, String name, String email) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
}
public Company() {
	super();
	
}

}
