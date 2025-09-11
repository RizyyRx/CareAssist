package com.hexaware.project.CareAssist.dto;

import java.time.LocalDate;

public class GetAllPatientInsuranceDTO {
	
	private int patientInsuranceId;
    private int patientId;
    private int insurancePlanId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

	public int getPatientInsuranceId() {
		return patientInsuranceId;
	}
	public void setPatientInsuranceId(int patientInsuranceId) {
		this.patientInsuranceId = patientInsuranceId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getInsurancePlanId() {
		return insurancePlanId;
	}
	public void setInsurancePlanId(int insurancePlanId) {
		this.insurancePlanId = insurancePlanId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public GetAllPatientInsuranceDTO() {
		super();
	}
	public GetAllPatientInsuranceDTO(int patientInsuranceId, int patientId, int insurancePlanId, LocalDate startDate,
			LocalDate endDate, String status) {
		super();
		this.patientInsuranceId = patientInsuranceId;
		this.patientId = patientId;
		this.insurancePlanId = insurancePlanId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

}
