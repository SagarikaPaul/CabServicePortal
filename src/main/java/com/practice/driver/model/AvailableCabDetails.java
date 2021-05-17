package com.practice.driver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailableCabDetails {
	
	private String name;
	
	@JsonProperty("phone_number")
	private Long phoneNumber;
	
	@JsonProperty("car_number")
	private String carNumber;

	public AvailableCabDetails() {
		
	}

	public AvailableCabDetails(String name, Long phoneNumber, String carNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.carNumber = carNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	
	

}
