package com.hexaware.project.CareAssist.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ClaimSubmissionDTO {
	
	@NotNull(message = "Invoice ID is required")
    private Integer invoiceId;

    @NotNull(message = "Insurance Plan ID is required")
    private Integer insurancePlanId;

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    @NotBlank(message = "Treatment is required")
    private String treatment;

    @NotNull(message = "Date of service is required")
    private LocalDate dateOfService;

    @NotNull(message = "Claim amount is required")
    @Positive
    private BigDecimal claimAmount;

    private String medicalDocuments; // JSON string or base64 encoded files

	
    public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getInsurancePlanId() {
		return insurancePlanId;
	}

	public void setInsurancePlanId(Integer insurancePlanId) {
		this.insurancePlanId = insurancePlanId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public LocalDate getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(LocalDate dateOfService) {
		this.dateOfService = dateOfService;
	}

	public BigDecimal getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getMedicalDocuments() {
		return medicalDocuments;
	}

	public void setMedicalDocuments(String medicalDocuments) {
		this.medicalDocuments = medicalDocuments;
	}



}
