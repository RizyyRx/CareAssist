package com.hexaware.project.CareAssist.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "patients")
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    @OneToOne // One patient can be one user only
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @NotNull(message = "User is required")
    private User user;
    
	@NotBlank(message = "First Name is required")
	private String firstName;
	
	@NotBlank(message = "Last Name is required")
	private String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(nullable = false)
    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    @Column(nullable = false, length = 10)
    private String gender;

    @NotBlank(message = "Contact number is required")
    @Size(min = 10, max = 15, message = "Contact number must be between 10–15 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Contact number must contain only digits")
    @Column(nullable = false, length = 15, unique = true)
    private String contactNumber;

    @NotBlank(message = "Address is required")
    @Column(nullable = false, length = 255)
    private String address;

    @Column(columnDefinition = "TEXT") // optional field
    private String medicalHistory;

    // One patient will have many patientInsurance records
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientInsurance> patientInsurance;

    // One patient will have many invoices
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Invoice> invoice;
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public List<PatientInsurance> getPatientInsurance() {
		return patientInsurance;
	}

	public void setPatientInsurance(List<PatientInsurance> patientInsurance) {
		this.patientInsurance = patientInsurance;
	}

	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}

	public Patient() {
		super();
	}

	public Patient(int patientId, @NotNull(message = "User is required") User user,
			@NotBlank(message = "First Name is required") String firstName,
			@NotBlank(message = "Last Name is required") String lastName,
			@NotNull(message = "Date of birth is required") @Past(message = "Date of birth must be in the past") LocalDate dob,
			@NotBlank(message = "Gender is required") String gender,
			@NotBlank(message = "Contact number is required") @Size(min = 10, max = 15, message = "Contact number must be between 10–15 digits") @Pattern(regexp = "^[0-9]+$", message = "Contact number must contain only digits") String contactNumber,
			@NotBlank(message = "Address is required") String address, String medicalHistory,
			List<PatientInsurance> patientInsurance, List<Invoice> invoice) {
		super();
		this.patientId = patientId;
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.address = address;
		this.medicalHistory = medicalHistory;
		this.patientInsurance = patientInsurance;
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", user=" + user + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dob=" + dob + ", gender=" + gender + ", contactNumber=" + contactNumber + ", address="
				+ address + ", medicalHistory=" + medicalHistory + ", patientInsurance=" + patientInsurance
				+ ", invoice=" + invoice + "]";
	}


    

}
