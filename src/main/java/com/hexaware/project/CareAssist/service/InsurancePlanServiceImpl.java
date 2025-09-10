package com.hexaware.project.CareAssist.service;

import org.springframework.stereotype.Service;

import com.hexaware.project.CareAssist.dto.InsurancePlanDTO;
import com.hexaware.project.CareAssist.entity.InsurancePlan;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.repository.InsurancePlanRepository;
import com.hexaware.project.CareAssist.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService{

	    public InsurancePlanServiceImpl(InsurancePlanRepository insurancePlanRepository) {
		super();
		this.insurancePlanRepository = insurancePlanRepository;
	}

		private InsurancePlanRepository insurancePlanRepository;

	    public String createInsurancePlan(User insuranceCompany,InsurancePlanDTO dto) {
	    	

	        InsurancePlan insurancePlan = new InsurancePlan();
	        insurancePlan.setInsuranceCompany(insuranceCompany);
	        insurancePlan.setPlanName(dto.getPlanName());
	        insurancePlan.setCoverageAmount(dto.getCoverageAmount());
	        insurancePlan.setPremiumAmount(dto.getPremiumAmount());
	        insurancePlan.setPolicyTerm(dto.getPolicyTerm());
	        insurancePlan.setDescription(dto.getDescription());
	        insurancePlanRepository.save(insurancePlan);

	        return "Insurance plan created successfully";
	    }
}
