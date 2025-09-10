package com.hexaware.project.CareAssist.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank(message = "First Name is required")
	private String firstName;
	
	@NotBlank(message = "Last Name is required")
	private String lastName;
	
	@NotBlank(message = "Username is required")
	@Column(unique = true)
	private String username;
	
	@NotBlank(message = "Email is required")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 3, max = 10, message = "Password must be between 3 to 15 characters")
	private String password;
	
	@NotBlank(message = "Role is required")
	private String role;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	// One user can be one patient only
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL) 
	private Patient patient;
	
	// One user can have many insurancePlans
	@OneToMany(mappedBy = "insuranceCompany", cascade = CascadeType.ALL)
	private List<InsurancePlan> insurancePlan;
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<InsurancePlan> getInsurancePlan() {
		return insurancePlan;
	}

	public void setInsurancePlan(List<InsurancePlan> insurancePlan) {
		this.insurancePlan = insurancePlan;
	}

	public User() {
		super();
	}

	public User(int userId, @NotBlank(message = "First Name is required") String firstName,
			@NotBlank(message = "Last Name is required") String lastName,
			@NotBlank(message = "Username is required") String username,
			@NotBlank(message = "Email is required") String email,
			@NotBlank(message = "Password is required") @Size(min = 3, max = 10, message = "Password must be between 3 to 15 characters") String password,
			@NotBlank(message = "Role is required") String role, LocalDateTime createdAt, Patient patient,
			List<InsurancePlan> insurancePlan) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
		this.patient = patient;
		this.insurancePlan = insurancePlan;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", email=" + email + ", password=" + password + ", role=" + role + ", createdAt="
				+ createdAt + ", patient=" + patient + ", insurancePlan=" + insurancePlan + "]";
	}



}
