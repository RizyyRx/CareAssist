package com.hexaware.project.CareAssist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.project.CareAssist.entity.Claim;
import com.hexaware.project.CareAssist.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	boolean existsByClaim(Claim claim);
}
