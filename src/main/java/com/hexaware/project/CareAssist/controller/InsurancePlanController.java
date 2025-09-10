package com.hexaware.project.CareAssist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import com.hexaware.project.CareAssist.dto.InsurancePlanDTO;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.repository.UserRepository;
import com.hexaware.project.CareAssist.service.InsurancePlanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/insuranceplan")
public class InsurancePlanController {

    public InsurancePlanController(InsurancePlanService insurancePlanService, UserRepository userRepository) {
		super();
		this.insurancePlanService = insurancePlanService;
		this.userRepository = userRepository;
	}


	private InsurancePlanService insurancePlanService;
    private UserRepository userRepository;

    @PreAuthorize("hasRole('INSURANCE_COMPANY')")
	@PostMapping("/create")
    public ResponseEntity<String> createInsurancePlan(@Valid @RequestBody InsurancePlanDTO insurancePlanDTO, Authentication authentication) {
		
		String username = authentication.getName(); // Extract from JWT
	    User user = userRepository.findByUsername(username)
	                 .orElseThrow(() -> new RuntimeException("User not found"));
	    
        String message =  insurancePlanService.createInsurancePlan(user, insurancePlanDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
