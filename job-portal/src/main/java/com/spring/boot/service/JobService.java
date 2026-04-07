package com.spring.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.spring.boot.entity.Job;
import com.spring.boot.repository.JobRepository;


@Service
public class JobService {
	@Autowired
private JobRepository jobRepository;
	
	
	public Job saveJob(Job job) {
		return jobRepository.save(job);	
	}
	public List<Job>filterJobs(List<String>skills,List<String>locations,Double minSalary){
		List<Job> jobs = jobRepository.findAll();
		
		return jobs.stream().filter(job->{
			//filter skills
			boolean skillMatch=(skills==null||skills.isEmpty())  ||
					skills.stream().anyMatch(skill->job.getSkills().toLowerCase().contains(skill.toLowerCase()));
		//filter location
			boolean locationMatch=(locations==null|| locations.isEmpty())||
					locations.contains(job.getLocation());
			//filter salary
			boolean salaryMatch = (minSalary == null) || job.getSalary() >= minSalary; return skillMatch && locationMatch && salaryMatch;
		
		}).toList();
		

	}
//	public List<Job>findJobByTitle(String title){
//		return jobRepository.findByTitleContainingIgnoreCase(title);
//		
//	}
//	public List<Job> findJobByLocation(String title){
//	return jobRepository.findByLocationContainingIgnoreCase(title);
//	}
//	public List<Job> findJobByTitleAndLocation(String title, String location){
//		return jobRepository.findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(title,location);
//		}
//	public List<Job> findJobBySalary(Double salary){
//		return jobRepository.findBySalaryGreaterThanEqual(salary);
//		}
	public Job findJobById(Long id){
		 return jobRepository.findById(id).orElse(null);
	}
	public List<Job> findAllJobs() {
		
		return  jobRepository.findAll();
	}
	public void deleteJob(Long id){
	    jobRepository.deleteById(id);
	}
}

