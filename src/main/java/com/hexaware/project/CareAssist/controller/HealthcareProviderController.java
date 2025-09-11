package com.hexaware.project.CareAssist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.project.CareAssist.dto.InvoiceDTO;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.repository.UserRepository;
import com.hexaware.project.CareAssist.service.HealthcareProviderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/provider")
public class HealthcareProviderController {

	public HealthcareProviderController(HealthcareProviderService healthcareProviderService, UserRepository userRepository) {
		super();
		this.healthcareProviderService = healthcareProviderService;
		this.userRepository = userRepository;
	}
	private HealthcareProviderService healthcareProviderService;
	private UserRepository userRepository;
	
	// Create an invoice
    @PreAuthorize("hasRole('HEALTHCARE_PROVIDER')")
    @PostMapping("/create-invoice")
    public ResponseEntity<String> createInvoice(@Valid @RequestBody InvoiceDTO dto, Authentication authentication) {
        String providerUsername = authentication.getName();

        User provider = userRepository.findByUsername(providerUsername)
                .orElseThrow(() -> new RuntimeException("Provider user not found"));

        String message = healthcareProviderService.createInvoice(provider, dto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
