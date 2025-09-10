package com.hexaware.project.CareAssist.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "insurance_plans")
public class InsurancePlan {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;

    @ManyToOne // Many insurancePlan will be there for one user
    @JoinColumn(name = "insurance_company_id", nullable = false)
    @NotNull(message = "Insurance company is required")
    private User insuranceCompany;

    @NotBlank(message = "Plan name is required")
    @Column(nullable = false)
    private String planName;

    @NotNull(message = "Coverage amount is required")
    @Positive(message = "Coverage amount must be positive")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal coverageAmount;

    @NotNull(message = "Premium amount is required")
    @Positive(message = "Premium amount must be positive")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal premiumAmount;

    @NotNull(message = "Policy term is required")
    @Positive(message = "Policy term must be positive")
    @Column(nullable = false)
    private int policyTerm;

    private String description; // Optional

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // One insurancePlan can have many patientInsurance records
    @OneToMany(mappedBy = "insurancePlan", cascade = CascadeType.ALL)
    private List<PatientInsurance> patientInsurance;

    public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public User getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(User insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public BigDecimal getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(BigDecimal coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	public BigDecimal getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(BigDecimal premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public int getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(int policyTerm) {
		this.policyTerm = policyTerm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<PatientInsurance> getPatientInsurance() {
		return patientInsurance;
	}

	public void setPatientInsurance(List<PatientInsurance> patientInsurance) {
		this.patientInsurance = patientInsurance;
	}

	public InsurancePlan() {
		super();
	}

	public InsurancePlan(int planId, @NotNull(message = "Insurance company is required") User insuranceCompany,
			@NotBlank(message = "Plan name is required") String planName,
			@NotNull(message = "Coverage amount is required") @Positive(message = "Coverage amount must be positive") BigDecimal coverageAmount,
			@NotNull(message = "Premium amount is required") @Positive(message = "Premium amount must be positive") BigDecimal premiumAmount,
			@NotNull(message = "Policy term is required") @Positive(message = "Policy term must be positive") int policyTerm,
			String description, LocalDateTime createdAt, List<PatientInsurance> patientInsurance) {
		super();
		this.planId = planId;
		this.insuranceCompany = insuranceCompany;
		this.planName = planName;
		this.coverageAmount = coverageAmount;
		this.premiumAmount = premiumAmount;
		this.policyTerm = policyTerm;
		this.description = description;
		this.createdAt = createdAt;
		this.patientInsurance = patientInsurance;
	}

	@Override
	public String toString() {
		return "InsurancePlan [planId=" + planId + ", insuranceCompany=" + insuranceCompany + ", planName=" + planName
				+ ", coverageAmount=" + coverageAmount + ", premiumAmount=" + premiumAmount + ", policyTerm="
				+ policyTerm + ", description=" + description + ", createdAt=" + createdAt + ", patientInsurance="
				+ patientInsurance + "]";
	}


}
