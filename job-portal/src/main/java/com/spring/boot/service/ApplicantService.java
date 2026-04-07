package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.entity.Applicant;
import com.spring.boot.repository.ApplicantRepository;

@Service
public class ApplicantService {
	@Autowired
	private ApplicantRepository  applicantRepository;
	
	public Applicant saveApplicant(Applicant applicant) {
		return applicantRepository.save(applicant);	
	}
	public List<Applicant>SeeAllApplicant(){
		return applicantRepository.findAll();
	}
	public Applicant findByEmail(String email) {
		 return applicantRepository.findByEmail(email);
	}
	public Applicant updateApplicant(String email,String newName,String newEmail) {
		Applicant applicant=applicantRepository.findByEmail(email);
		applicant.setEmail(newEmail);
		applicant.setName(newName);
		return applicantRepository.save(applicant);	
		
	}

	public String deleteApplicant(Applicant applicant) {
		applicantRepository.delete(applicant);	
		return "Applicant deleted successfully!";
		
	}
	public void checkDuplicatEmail(String email) {
		boolean isExist=applicantRepository.existsByEmail(email);
		if(isExist==true)return ;
		else {
			Applicant applicant = new Applicant();
			applicant.setEmail(email);
			applicantRepository.save(applicant);
		}
		
	}
}
