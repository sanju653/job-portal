package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.entity.Applicant;
import com.spring.boot.service.ApplicantService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private ApplicantService applicantService;
	@GetMapping("/login")
		public String Login() {
		return "login";
	}
	
	@PostMapping("/dologin")
	public String doLogin(@RequestParam String email,
            @RequestParam String password,
            Model model, HttpSession session) {
		
		 Applicant applicant = applicantService.findByEmail(email);
		 
		 if (applicant == null) {
		        model.addAttribute("error", "No user found");
		        return "login";
		    }

		    // ❌ Wrong password
		    if (!applicant.getPassword().equals(password)) {
		        model.addAttribute("error1", "Invalid password");
		        return "login";
		    }

		 
		 if (applicant != null && applicant.getPassword().equals(password)) {
		     
			 // ✅ STORE DATA IN SESSION
		        session.setAttribute("applicant", applicant);
		        session.setAttribute("role", applicant.getRole());
			
		 }
		 return "redirect:/jobs"; // success
		 } 
//		 else {
//		        model.addAttribute("error", "Invalid email or password");		        return "login";
//		
//}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // destroy session
	    return "redirect:/login";
	}

}
