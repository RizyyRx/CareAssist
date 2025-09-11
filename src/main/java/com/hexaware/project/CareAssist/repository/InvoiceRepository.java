package com.hexaware.project.CareAssist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.project.CareAssist.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

}
