package com.hexaware.project.CareAssist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.project.CareAssist.dto.InsurancePlanDTO;
import com.hexaware.project.CareAssist.entity.InsurancePlan;
import com.hexaware.project.CareAssist.entity.User;
import com.hexaware.project.CareAssist.repository.InsurancePlanRepository;


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
	    
		public List<InsurancePlanDTO> getAllInsurancePlans() {
	        List<InsurancePlan> plans = insurancePlanRepository.findAll();
	        return plans.stream().map(plan -> {
	            InsurancePlanDTO dto = new InsurancePlanDTO();
	            dto.setPlanName(plan.getPlanName());
	            dto.setCoverageAmount(plan.getCoverageAmount());
	            dto.setPremiumAmount(plan.getPremiumAmount());
	            dto.setPolicyTerm(plan.getPolicyTerm());
	            dto.setDescription(plan.getDescription());
	            return dto;
	        }).toList();
	    }
}
