package com.hexaware.project.CareAssist.service;



import org.springframework.stereotype.Service;
import com.hexaware.project.CareAssist.dto.PatientUpdateDTO;
import com.hexaware.project.CareAssist.entity.Patient;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.repository.InsurancePlanRepository;
import com.hexaware.project.CareAssist.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{
	
	public PatientServiceImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;

	}

	private PatientRepository patientRepository;


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
	

}
