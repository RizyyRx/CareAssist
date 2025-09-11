package com.hexaware.project.CareAssist.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "invoices")
public class Invoice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceId;

    @ManyToOne // Many invoices for one patient
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Patient is required")
    private Patient patient;

    @ManyToOne  // Many invoices for one provider (User with role="PROVIDER")
    @JoinColumn(name = "provider_id", nullable = false)
    @NotNull(message = "Provider is required")
    private User provider;

    @NotBlank(message = "Invoice number is required")
    @Column(nullable = false, unique = true, length = 50)
    private String invoiceNumber;

    @NotNull(message = "Invoice date is required")
    @Column(nullable = false, updatable = false)
    private LocalDate invoiceDate;

    @NotNull(message = "Due date is required")
    @Column(nullable = false)
    private LocalDate dueDate;

    // Fee breakdown (optional, can be null if not applicable)
    @Positive(message = "Consultation fee must be positive")
    @Column(precision = 12, scale = 2)
    private BigDecimal consultationFee;

    @Positive(message = "Diagnostic tests fee must be positive")
    @Column(precision = 12, scale = 2)
    private BigDecimal diagnosticTestsFee;

    @Positive(message = "Diagnostic scan fee must be positive")
    @Column(precision = 12, scale = 2)
    private BigDecimal diagnosticScanFee;

    @Positive(message = "Medication fee must be positive")
    @Column(precision = 12, scale = 2)
    private BigDecimal medicationFee;

    // Calculated amounts
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal tax;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private String status = "PENDING";
    
    @PrePersist
    public void prePersist() {
        calculateTotals();      // calculate subtotal, tax, totalAmount
        calculateDueDate();     // calculate dueDate based on invoiceDate
    }

    @PreUpdate
    public void calculateTotals() {
        this.subtotal = (consultationFee == null ? BigDecimal.ZERO : consultationFee)
                    .add(diagnosticTestsFee == null ? BigDecimal.ZERO : diagnosticTestsFee)
                    .add(diagnosticScanFee == null ? BigDecimal.ZERO : diagnosticScanFee)
                    .add(medicationFee == null ? BigDecimal.ZERO : medicationFee);
        this.tax = subtotal.multiply(new BigDecimal("0.08"));
        this.totalAmount = subtotal.add(tax);
    }

    private void calculateDueDate() {
        if (invoiceDate != null) {
            this.dueDate = invoiceDate.plusDays(7);
        }
    }
	
	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public User getProvider() {
		return provider;
	}

	public void setProvider(User provider) {
		this.provider = provider;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(BigDecimal consultationFee) {
		this.consultationFee = consultationFee;
	}

	public BigDecimal getDiagnosticTestsFee() {
		return diagnosticTestsFee;
	}

	public void setDiagnosticTestsFee(BigDecimal diagnosticTestsFee) {
		this.diagnosticTestsFee = diagnosticTestsFee;
	}

	public BigDecimal getDiagnosticScanFee() {
		return diagnosticScanFee;
	}

	public void setDiagnosticScanFee(BigDecimal diagnosticScanFee) {
		this.diagnosticScanFee = diagnosticScanFee;
	}

	public BigDecimal getMedicationFee() {
		return medicationFee;
	}

	public void setMedicationFee(BigDecimal medicationFee) {
		this.medicationFee = medicationFee;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Invoice() {
		super();
	}

	public Invoice(int invoiceId, @NotNull(message = "Patient is required") Patient patient,
			@NotNull(message = "Provider is required") User provider,
			@NotBlank(message = "Invoice number is required") String invoiceNumber,
			@NotNull(message = "Invoice date is required") LocalDate invoiceDate,
			@NotNull(message = "Due date is required") LocalDate dueDate,
			@Positive(message = "Consultation fee must be positive") BigDecimal consultationFee,
			@Positive(message = "Diagnostic tests fee must be positive") BigDecimal diagnosticTestsFee,
			@Positive(message = "Diagnostic scan fee must be positive") BigDecimal diagnosticScanFee,
			@Positive(message = "Medication fee must be positive") BigDecimal medicationFee, BigDecimal subtotal,
			BigDecimal tax, BigDecimal totalAmount, @NotBlank(message = "Status is required") String status) {
		super();
		this.invoiceId = invoiceId;
		this.patient = patient;
		this.provider = provider;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
		this.consultationFee = consultationFee;
		this.diagnosticTestsFee = diagnosticTestsFee;
		this.diagnosticScanFee = diagnosticScanFee;
		this.medicationFee = medicationFee;
		this.subtotal = subtotal;
		this.tax = tax;
		this.totalAmount = totalAmount;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", patient=" + patient + ", provider=" + provider
				+ ", invoiceNumber=" + invoiceNumber + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate
				+ ", consultationFee=" + consultationFee + ", diagnosticTestsFee=" + diagnosticTestsFee
				+ ", diagnosticScanFee=" + diagnosticScanFee + ", medicationFee=" + medicationFee + ", subtotal="
				+ subtotal + ", tax=" + tax + ", totalAmount=" + totalAmount + ", status=" + status + "]";
	}


}
