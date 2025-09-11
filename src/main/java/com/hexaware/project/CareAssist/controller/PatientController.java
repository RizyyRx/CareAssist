package com.hexaware.project.CareAssist.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.project.CareAssist.dto.ClaimSubmissionDTO;
import com.hexaware.project.CareAssist.dto.InvoiceViewDTO;
import com.hexaware.project.CareAssist.dto.PatientInsuranceDTO;
import com.hexaware.project.CareAssist.dto.PatientUpdateDTO;
import com.hexaware.project.CareAssist.entity.Invoice;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.repository.UserRepository;
import com.hexaware.project.CareAssist.service.PatientService;

import jakarta.validation.Valid;

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

	
	// Update patient profile
	@PreAuthorize("hasRole('PATIENT')")
	@PutMapping("/update-profile")
	public ResponseEntity<String> updateProfile(@RequestBody PatientUpdateDTO dto,Authentication authentication) {

	    String username = authentication.getName(); // Extract from JWT
	    User user = userRepository.findByUsername(username)
	                 .orElseThrow(() -> new RuntimeException("User not found"));

	    String response = patientService.updatePatientProfile(user, dto);
	    return ResponseEntity.ok(response);
	}
	
	// Select an Insurance plan
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/select-plan")
	public ResponseEntity<String> selectInsurancePlan(@Valid @RequestBody PatientInsuranceDTO dto, Authentication authentication) {
		
	    String username = authentication.getName(); // Extract from JWT
	    User user = userRepository.findByUsername(username)
	                 .orElseThrow(() -> new RuntimeException("User not found"));

	    String message = patientService.selectInsurancePlan(user, dto);
	    return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	// Get current patient invoices
	@GetMapping("/invoices")
    public ResponseEntity<List<InvoiceViewDTO>> getPatientInvoices(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        List<InvoiceViewDTO> invoices = patientService.getInvoices(user);
        return ResponseEntity.ok(invoices);
    }
	
	// Submit a claim
	 @PreAuthorize("hasRole('PATIENT')")
	    @PostMapping("/submit-claim")
	    public ResponseEntity<String> submitClaim(@Valid @RequestBody ClaimSubmissionDTO dto,Authentication authentication) {
	        String username = authentication.getName();
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        String message = patientService.submitClaim(user, dto);
	        return new ResponseEntity<>(message, HttpStatus.CREATED);
	    }
	 
	 // Mark status as PAID in invoice
	 @PreAuthorize("hasRole('PATIENT')")
	 @PatchMapping("/invoice/mark-paid/{invoiceId}")
	 public ResponseEntity<String> markInvoicePaid(@PathVariable int invoiceId,Authentication authentication) {

		 String username = authentication.getName();
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	     String message = patientService.markInvoiceAsPaid(invoiceId, user);

	     return ResponseEntity.ok(message);
	 }
	
}
