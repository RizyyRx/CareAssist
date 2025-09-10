package com.hexaware.project.CareAssist.service;

import java.util.List;

import com.hexaware.project.CareAssist.dto.InsurancePlanDTO;
import com.hexaware.project.CareAssist.entity.InsurancePlan;
import com.hexaware.project.CareAssist.entity.User;

public interface InsurancePlanService {

	String createInsurancePlan(User insuranceCompany,InsurancePlanDTO dto);
	
	List<InsurancePlanDTO> getAllInsurancePlans();
	
}
