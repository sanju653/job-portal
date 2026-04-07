package com.spring.boot.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @ManyToOne
	private Job job;
	 @ManyToOne
	private Applicant applicant;
	public Application( Job job, Applicant applicant) {
		super();
		
		this.job = job;
		this.applicant = applicant;
	}
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	
public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	//	public void setId(Long id) {
//		this.id = id;
//	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	

}
