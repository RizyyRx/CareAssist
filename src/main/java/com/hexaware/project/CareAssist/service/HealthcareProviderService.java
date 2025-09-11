package com.hexaware.project.CareAssist.service;

import com.hexaware.project.CareAssist.dto.InvoiceDTO;
import com.hexaware.project.CareAssist.entity.User;

public interface HealthcareProviderService {

	String createInvoice(User provider, InvoiceDTO dto);
}
