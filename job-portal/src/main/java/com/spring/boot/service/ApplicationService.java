package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.entity.Applicant;
import com.spring.boot.entity.Application;
import com.spring.boot.entity.Job;
import com.spring.boot.repository.ApplicantRepository;
import com.spring.boot.repository.ApplicationRepository;
import com.spring.boot.repository.JobRepository;

@Service
public class ApplicationService {
	@Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private JobRepository jobRepository;
    
    public Application save(Application application) {
        return applicationRepository.save(application);
    }
    // 🔹 Find all applications by applicant
    public List<Application> findAllApplicationByApplicant( Long applicantId ){
    	return applicationRepository.findByApplicantId(applicantId);
    }
 // 🔹 Find all applications for a job
    public List<Application> findAllApplicationByJob( Long jobId ){
    	return applicationRepository.findByJobId(jobId);
    }
    public Application checkDuplicateApply(Long applicantId, Long jobId) {
    	
    	if (applicationRepository.existsByApplicantIdAndJobId(applicantId, jobId)) {
            throw new RuntimeException("You have already applied for this job!");
        }
    	// Fetch applicant
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        //  Fetch job
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Create application
        Application application = new Application();
        application.setApplicant(applicant);
        application.setJob(job);
        

        // 🔹 Step 5: Save
        return applicationRepository.save(application);
    }
    }
    
    



