package com.hexaware.project.CareAssist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hexaware.project.CareAssist.dto.GetAllClaimHistoryDTO;
import com.hexaware.project.CareAssist.dto.GetAllInvoiceDTO;
import com.hexaware.project.CareAssist.dto.GetAllPatientDTO;
import com.hexaware.project.CareAssist.dto.GetAllPatientInsuranceDTO;
import com.hexaware.project.CareAssist.dto.GetAllPaymentDTO;
import com.hexaware.project.CareAssist.dto.GetAllUserDTO;
import com.hexaware.project.CareAssist.entity.Claim;
import com.hexaware.project.CareAssist.entity.Payment;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.repository.ClaimRepository;
import com.hexaware.project.CareAssist.repository.InvoiceRepository;
import com.hexaware.project.CareAssist.repository.PatientInsuranceRepository;
import com.hexaware.project.CareAssist.repository.PatientRepository;
import com.hexaware.project.CareAssist.repository.PaymentRepository;
import com.hexaware.project.CareAssist.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService{


	public AdminServiceImpl(UserRepository userRepository, ClaimRepository claimRepository,
			PaymentRepository paymentRepository, PatientRepository patientRepository,
			PatientInsuranceRepository patientInsuranceRepository, InvoiceRepository invoiceRepository) {
		super();
		this.userRepository = userRepository;
		this.claimRepository = claimRepository;
		this.paymentRepository = paymentRepository;
		this.patientRepository = patientRepository;
		this.patientInsuranceRepository = patientInsuranceRepository;
		this.invoiceRepository = invoiceRepository;
	}


	private UserRepository userRepository;
	private ClaimRepository claimRepository;
	private PaymentRepository paymentRepository;
	private PatientRepository patientRepository;
	private PatientInsuranceRepository patientInsuranceRepository;
	private InvoiceRepository invoiceRepository;

	public List<GetAllUserDTO> getAllUsers() {
	    List<User> users = userRepository.findAll();

	    return users.stream().map(user -> {
	        GetAllUserDTO dto = new GetAllUserDTO();
	        dto.setUserId(user.getUserId());
	        dto.setUsername(user.getUsername());
	        dto.setEmail(user.getEmail());
	        dto.setCreatedAt(user.getCreatedAt());
	        return dto;
	    }).collect(Collectors.toList());
	}

	public List<GetAllClaimHistoryDTO> getAllClaims() {
	    List<Claim> claims = claimRepository.findAll();

	    return claims.stream()
	        .map(c -> new GetAllClaimHistoryDTO(
	            c.getClaimId(),
	            c.getClaimAmount(),
	            c.getInvoiceAmount(),
	            c.getDateOfService(),
	            c.getDiagnosis(),
	            c.getTreatment(),
	            c.getStatus(),
	            c.getSubmittedAt(),
	            c.getReviewedAt(),
	            c.getApprovedAt()
	        ))
	        .collect(Collectors.toList());
	}
	
	public List<GetAllPaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream()
            .map(p -> new GetAllPaymentDTO(
                p.getPaymentId(),
                p.getClaim().getClaimId(),
                p.getInsuranceCompany().getUserId(),
                p.getPatient().getPatientId(),
                p.getAmountPaid(),
                p.getPaymentDate(),
                p.getTransactionRef()
            )).collect(Collectors.toList());
    }
	
	
    public List<GetAllPatientDTO> getAllPatients() {
        return patientRepository.findAll()
            .stream()
            .map(p -> new GetAllPatientDTO(
                p.getPatientId(),
                p.getUser().getUserId(),
                p.getFirstName(),
                p.getLastName(),
                p.getDob(),
                p.getGender(),
                p.getContactNumber(),
                p.getAddress(),
                p.getMedicalHistory()
            )).toList();
    }

    
    public List<GetAllPatientInsuranceDTO> getAllPatientInsurances() {
        return patientInsuranceRepository.findAll()
            .stream()
            .map(pi -> new GetAllPatientInsuranceDTO(
                pi.getPatientInsuranceId(),
                pi.getPatient().getPatientId(),
                pi.getInsurancePlan().getPlanId(),
                pi.getStartDate(),
                pi.getEndDate(),
                pi.getStatus()
            )).toList();
    }

    
    public List<GetAllInvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll()
            .stream()
            .map(inv -> new GetAllInvoiceDTO(
                inv.getInvoiceId(),
                inv.getPatient().getPatientId(),
                inv.getProvider().getUserId(),
                inv.getInvoiceNumber(),
                inv.getInvoiceDate(),
                inv.getDueDate(),
                inv.getConsultationFee(),
                inv.getDiagnosticTestsFee(),
                inv.getDiagnosticScanFee(),
                inv.getMedicationFee(),
                inv.getSubtotal(),
                inv.getTax(),
                inv.getTotalAmount(),
                inv.getStatus()
            )).toList();
    }

    
    public List<GetAllPatientInsuranceDTO> getPatientInsurancesByPatientId(int patientId) {
        return patientInsuranceRepository.findByPatientPatientId(patientId)
            .stream()
            .map(pi -> new GetAllPatientInsuranceDTO(
                pi.getPatientInsuranceId(),
                pi.getPatient().getPatientId(),
                pi.getInsurancePlan().getPlanId(),
                pi.getStartDate(),
                pi.getEndDate(),
                pi.getStatus()
            )).toList();
    }

    
    public List<GetAllInvoiceDTO> getInvoicesByPatientId(int patientId) {
        return invoiceRepository.findByPatientPatientId(patientId)
            .stream()
            .map(inv -> new GetAllInvoiceDTO(
                inv.getInvoiceId(),
                inv.getPatient().getPatientId(),
                inv.getProvider().getUserId(),
                inv.getInvoiceNumber(),
                inv.getInvoiceDate(),
                inv.getDueDate(),
                inv.getConsultationFee(),
                inv.getDiagnosticTestsFee(),
                inv.getDiagnosticScanFee(),
                inv.getMedicationFee(),
                inv.getSubtotal(),
                inv.getTax(),
                inv.getTotalAmount(),
                inv.getStatus()
            )).toList();
    }
	
}
