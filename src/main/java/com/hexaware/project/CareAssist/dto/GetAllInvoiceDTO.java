package com.hexaware.project.CareAssist.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GetAllInvoiceDTO {
	
	private int invoiceId;
    private int patientId;
    private int providerId;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private BigDecimal consultationFee;
    private BigDecimal diagnosticTestsFee;
    private BigDecimal diagnosticScanFee;
    private BigDecimal medicationFee;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal totalAmount;
    private String status;

	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
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
	public GetAllInvoiceDTO() {
		super();
	}
	public GetAllInvoiceDTO(int invoiceId, int patientId, int providerId, String invoiceNumber, LocalDate invoiceDate,
			LocalDate dueDate, BigDecimal consultationFee, BigDecimal diagnosticTestsFee, BigDecimal diagnosticScanFee,
			BigDecimal medicationFee, BigDecimal subtotal, BigDecimal tax, BigDecimal totalAmount, String status) {
		super();
		this.invoiceId = invoiceId;
		this.patientId = patientId;
		this.providerId = providerId;
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

}
