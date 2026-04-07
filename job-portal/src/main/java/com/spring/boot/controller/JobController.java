package com.spring.boot.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.entity.Applicant;
import com.spring.boot.entity.Application;
import com.spring.boot.entity.Job;
import com.spring.boot.service.ApplicantService;
import com.spring.boot.service.ApplicationService;
import com.spring.boot.service.JobService;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class JobController {
	@Autowired
	private JobService jobService;
	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private ApplicationService applicationService;
	
	//*****************viewJobs******************
	@GetMapping("/jobs")
	public String viewJobs(Model model) {
		List<Job>jobs=jobService.findAllJobs();
		model.addAttribute("jobs", jobs);
		return "jobs";
		
	}
	//*****************saveJobs******************
	@GetMapping("/save")
	public String saveForm(HttpSession session) {
		 String role = (String) session.getAttribute("role");

		    if (!"RECRUITER".equals(role) && !"ADMIN".equals(role)) {
		        return "redirect:/jobs";
		    }
		return "save";
	}
	@PostMapping("/save")
	public String saveJobs(@ModelAttribute Job job, HttpSession session) {
		 String role = (String) session.getAttribute("role");
		 if (!"RECRUITER".equals(role) && !"ADMIN".equals(role)) {
		        return "redirect:/jobs"; // ❌ block USER
		    }
		 jobService.saveJob(job);
		 return "redirect:/jobs";
		 
		
		 
	}
	
	
	//*********************applyJobs********************
	@GetMapping("/apply/{id}")
	public String applyJob(@PathVariable Long id ,
					Model model,HttpSession session) {
		
		
		  if (session.getAttribute("USER") == null) {
		        return "redirect:/login";
		    }
		model.addAttribute("jobId",id);
		model.addAttribute("applicant",new Applicant());
		return "apply-form";
	}
	//****************************applyjob(taking details)*********
	@PostMapping("/apply/{id}")
	public String getDetails(@PathVariable Long id,
			@Valid @ModelAttribute Applicant applicant,
			BindingResult result, @RequestParam("resume") MultipartFile file,Model model) throws Exception{
		
		 if (result.hasErrors()) {
		        model.addAttribute("jobId", id);
		        return "apply-form";
		    }

		 if (file.isEmpty()) {
		        throw new RuntimeException();
		    }

		    Job job = jobService.findJobById(id);

		    if(job == null){
		         throw new RuntimeException();
		    }

		 String uploadDir = "uploads/resumes/";
		    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

		    Path path = Paths.get(uploadDir, fileName);
		    Files.createDirectories(path.getParent());
		    Files.write(path, file.getBytes());
		    applicant.setResumePath(fileName);
		    
		Applicant savedApplicant=applicantService.saveApplicant(applicant);
//		Job job=jobService.findJobById(id);
//		if (job == null) {
//		    throw new RuntimeException();
//		}
		Application application = new Application();
	    application.setApplicant(savedApplicant);
	    application.setJob(job);
	    applicationService.save(application);

	    return "redirect:/jobs";

	}
	@GetMapping("/searchjobs")
	public String searchJob(Model model) {
		  List<Job> jobs = jobService.findAllJobs();
		
		  Set<String> skills = new HashSet<>();
		    Set<String> locations = new HashSet<>();
		  
		    for (Job job : jobs) {
		        if (job.getSkills() != null) {
		            skills.add(job.getSkills());
		        }
		        locations.add(job.getLocation());
		    }
		  
		    model.addAttribute("skills", skills);
		    model.addAttribute("locations", locations);
		  return "searchjob";
	}
	
	
	@PostMapping("/searchjobs")
	public String selectJob(@RequestParam(required=false)List<String>skills,
							@RequestParam(required = false) List<String> locations, 
							@RequestParam(required = false) Double minSalary,
								Model model) {
		List<Job> jobs = jobService.filterJobs(skills, locations, minSalary);
		model.addAttribute("jobs", jobs);
		
		return "filteredjobs";
	}
	@GetMapping("/updatejob/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model,HttpSession session) {

		 String role = (String) session.getAttribute("role");

		    if (role == null || !role.equalsIgnoreCase("ADMIN")) {
		        return "redirect:/login";
		    }
		
	    Job job = jobService.findJobById(id);// 🔥 fetch from DB
	    if(job == null){
	         throw new RuntimeException();
	    }
	    
	    model.addAttribute("job", job);
	   System.out.println("jod id is"+job.getId());

	    return "updatejob"; 
	}
		
		
	@PostMapping("/updatejob")
	public String updateJob(@ModelAttribute Job job, HttpSession session,Model model) {
		String role = (String) session.getAttribute("role");

	    if (!"RECRUITER".equals(role) && !"ADMIN".equals(role)) {
	        return "redirect:/jobs"; // ❌ block USER
	    }
	    System.out.println("Role: " + session.getAttribute("role"));
	    if (job.getId() == null) {
	        throw new RuntimeException();
	    }
	   
	    System.out.println("Incoming ID: " + job.getId());

	    jobService.saveJob(job);

	    return "redirect:/jobs"; 
	}
	
	
	
	@GetMapping("/deletejob/{id}")
	public String deleteJob(@PathVariable Long id, HttpSession session) {

	    String role = (String) session.getAttribute("role");

	    if (!"RECRUITER".equals(role) && !"ADMIN".equals(role)) {
	        return "redirect:/jobs"; // ❌ block USER
	    }
	    Job job = jobService.findJobById(id);

	    if (job == null) {
	        throw new RuntimeException();
	    }

	    jobService.deleteJob(id);

	    return "redirect:/jobs";
	}
	
	
	
	
	
	
	
	
	
	
	
}
