package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.boot.entity.Applicant;
import com.spring.boot.service.ApplicantService;

import jakarta.validation.Valid;

@Controller
public class RegisterController {
	@Autowired
	private ApplicantService applicantService;
	@GetMapping("/register")
		public String registerForm(Model model) {
		model.addAttribute("applicant", new Applicant());
			return "register";
		}
	
	@PostMapping("/doregister")
	public String doregister( @Valid @ModelAttribute Applicant applicant,
            BindingResult result,
            Model model) {
		 if (result.hasErrors()) {
		       // return "register";
			 // back to form
		    }
		applicant.setRole("USER");
		applicantService.saveApplicant(applicant);
		return "redirect:/login";
		
	}
	
	
	
	
	
	
	}


