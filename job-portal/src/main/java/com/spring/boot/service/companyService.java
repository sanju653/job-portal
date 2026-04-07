package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.entity.Company;
import com.spring.boot.entity.Job;
import com.spring.boot.repository.CompanyRepository;
import com.spring.boot.repository.JobRepository;
@Service
public class companyService {
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
private JobRepository jobRepository;
	
	
	public List<Company>findAllCompany(){
		return companyRepository.findAll();
	}
	public  Company findByEmail(String email) {
		return companyRepository.findByEmail(email);
	}
public  List<Company> findByName(String name) {
	return companyRepository.findByNameContainingIgnoreCase(name);
}
//public  List<Company> findBylocation(String location) {
//	return companyRepository.findByLocationContainingIgnoreCase(location);
//}



public Company getCompanyByJobId(Long jobId) {
    Job job = jobRepository.findById(jobId)
            .orElseThrow(() -> new RuntimeException("Job not found"));

    return job.getCompany();
}










}
