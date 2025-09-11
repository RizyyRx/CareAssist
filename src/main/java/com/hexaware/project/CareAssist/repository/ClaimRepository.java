package com.hexaware.project.CareAssist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.project.CareAssist.entity.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Integer>{

}
