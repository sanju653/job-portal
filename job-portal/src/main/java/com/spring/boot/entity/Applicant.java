package com.spring.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Applicant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @NotBlank(message = "Name is required")
	    @Pattern(regexp = "^[A-Za-z ]+$", message = "Only letters allowed")
	private String name;
	
	 @Column(unique = true, nullable = false)
	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
	private String email;
	 
	 
	 
	 @NotBlank(message = "Password is required")
	   private String password;
	 
	private String role;
private String resumePath;
	
	public String getResumePath() {
		return resumePath;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public Applicant() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
