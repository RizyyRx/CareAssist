package com.hexaware.project.CareAssist.service;



import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hexaware.project.CareAssist.dto.PatientInsuranceDTO;
import com.hexaware.project.CareAssist.dto.PatientUpdateDTO;
import com.hexaware.project.CareAssist.entity.InsurancePlan;
import com.hexaware.project.CareAssist.entity.Patient;
import com.hexaware.project.CareAssist.entity.PatientInsurance;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.repository.InsurancePlanRepository;
import com.hexaware.project.CareAssist.repository.PatientInsuranceRepository;
import com.hexaware.project.CareAssist.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{
	

	public PatientServiceImpl(PatientRepository patientRepository, InsurancePlanRepository insurancePlanRepository,
			PatientInsuranceRepository patientInsuranceRepository) {
		super();
		this.patientRepository = patientRepository;
		this.insurancePlanRepository = insurancePlanRepository;
		this.patientInsuranceRepository = patientInsuranceRepository;
	}

	private PatientRepository patientRepository;
	private InsurancePlanRepository insurancePlanRepository;
	private PatientInsuranceRepository patientInsuranceRepository;


	public String updatePatientProfile(User user, PatientUpdateDTO dto) {

		// If patient record is present, use that to update, else creates new patient object and sets the record
	    Patient patient = patientRepository.findByUser(user)
	                      .orElse(new Patient()); // create if not exists
	    patient.setUser(user);
	    patient.setFirstName(dto.getFirstName());
	    patient.setLastName(dto.getLastName());
	    patient.setDob(dto.getDob());
	    patient.setGender(dto.getGender());
	    patient.setContactNumber(dto.getContactNumber());
	    patient.setAddress(dto.getAddress());
	    patient.setMedicalHistory(dto.getMedicalHistory());

	    patientRepository.save(patient);

	    return "Patient profile updated successfully for user: " + user.getUsername();
	}


	public String selectInsurancePlan(User user, PatientInsuranceDTO patientInsuranceDTO) {
		
		//user has linked patient entity
		Patient patient = user.getPatient();
		if (patient == null) {
	        throw new RuntimeException("No patient found for user");
	    }
		
		InsurancePlan insurancePlan = insurancePlanRepository.findById(patientInsuranceDTO.getPlanId())
				.orElseThrow(() -> new RuntimeException("Insurance Plan not found"));
		
		
		PatientInsurance patientInsurance = new PatientInsurance();
		
		patientInsurance.setPatient(patient);
		patientInsurance.setInsurancePlan(insurancePlan);
		
	    // Manually set startDate so @PrePersist can use it
	    patientInsurance.setStartDate(LocalDate.now());
		
		patientInsuranceRepository.save(patientInsurance);
		
		return "Insurance plan selected successfully";
		
	}
	

}
