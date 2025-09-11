package com.hexaware.project.CareAssist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.project.CareAssist.entity.InsurancePlan;

public interface InsurancePlanRepository extends JpaRepository<InsurancePlan, Integer>{

	Optional<InsurancePlan> findByPlanId(int planId);
}
