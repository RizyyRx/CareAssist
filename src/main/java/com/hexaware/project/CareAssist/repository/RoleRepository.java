package com.hexaware.project.CareAssist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.project.CareAssist.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
}
