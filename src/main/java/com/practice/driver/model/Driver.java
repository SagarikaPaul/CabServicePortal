package com.practice.driver.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message ="name cant be blank")
	private String name;
	
	//@Column(unique = true)
	
	@NotBlank(message ="email cant be blank")
	@Email(message = "Invalid format of email")
	private String email;
	
	@JsonProperty("phone_number")
	private Long phoneNumber;
	
	@NotBlank(message ="license_number cant be blank")
	@JsonProperty("license_number")
	private String licenseNumber;
	
	@NotBlank(message ="car_number cant be blank")
	@JsonProperty("car_number")
	private String carNumber;
	
	@OneToOne(mappedBy ="driver", cascade = CascadeType.ALL)
	@JsonIgnore
	private DriverLocation location;
	
	public Driver() {
		
	}

	public Driver(Long id, String name, String email, Long phoneNumber, String licenseNumber, String carNumber, DriverLocation location) {
		this.id=id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.licenseNumber = licenseNumber;
		this.carNumber = carNumber;
		this.location=location;
	}

	public Long getId() {
		return id;
	}
	
	public void setId( Long id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public DriverLocation getLocation() {
		return location;
	}

	public void setLocation(DriverLocation location) {
		this.location = location;
	}

	

}
