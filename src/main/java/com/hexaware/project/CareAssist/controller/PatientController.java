package com.hexaware.project.CareAssist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.project.CareAssist.dto.PatientUpdateDTO;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.repository.UserRepository;
import com.hexaware.project.CareAssist.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	public PatientController(UserRepository userRepository, PatientService patientService) {
		super();
		this.userRepository = userRepository;
		this.patientService = patientService;
	}

	private UserRepository userRepository;
	private PatientService patientService;

	@PreAuthorize("hasRole('PATIENT')")
	@PutMapping("/updateprofile")
	public ResponseEntity<String> updateProfile(@RequestBody PatientUpdateDTO dto,Authentication authentication) {

	    String username = authentication.getName(); // Extract from JWT
	    User user = userRepository.findByUsername(username)
	                 .orElseThrow(() -> new RuntimeException("User not found"));

	    String response = patientService.updatePatientProfile(user, dto);
	    return ResponseEntity.ok(response);
	}
	
}
