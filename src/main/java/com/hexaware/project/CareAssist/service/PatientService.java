package com.hexaware.project.CareAssist.service;

import com.hexaware.project.CareAssist.dto.PatientInsuranceDTO;
import com.hexaware.project.CareAssist.dto.PatientUpdateDTO;
import com.hexaware.project.CareAssist.entity.User;

public interface PatientService {

	String updatePatientProfile(User user, PatientUpdateDTO patientUpdateDTO);
	
	String selectInsurancePlan(User user, PatientInsuranceDTO patientInsuranceDTO);
	

}
