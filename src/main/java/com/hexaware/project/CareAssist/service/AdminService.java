package com.hexaware.project.CareAssist.service;

import java.util.List;

import com.hexaware.project.CareAssist.dto.GetAllClaimHistoryDTO;
import com.hexaware.project.CareAssist.dto.GetAllInvoiceDTO;
import com.hexaware.project.CareAssist.dto.GetAllPatientDTO;
import com.hexaware.project.CareAssist.dto.GetAllPatientInsuranceDTO;
import com.hexaware.project.CareAssist.dto.GetAllPaymentDTO;
import com.hexaware.project.CareAssist.dto.GetAllUserDTO;

public interface AdminService {

	List<GetAllUserDTO> getAllUsers();
	
	List<GetAllClaimHistoryDTO> getAllClaims();
	
	List<GetAllPaymentDTO> getAllPayments();
	
    List<GetAllPatientDTO> getAllPatients();

    List<GetAllPatientInsuranceDTO> getAllPatientInsurances();

    List<GetAllInvoiceDTO> getAllInvoices();

    List<GetAllPatientInsuranceDTO> getPatientInsurancesByPatientId(int patientId);

    List<GetAllInvoiceDTO> getInvoicesByPatientId(int patientId);
}
