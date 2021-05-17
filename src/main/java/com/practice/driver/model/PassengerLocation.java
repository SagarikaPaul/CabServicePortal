package com.practice.driver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PassengerLocation {
	
	@JsonProperty("latitude")
	private double passLatitude;
	@JsonProperty("longitude")
	private double passLongitude;
	
	public PassengerLocation() {
		
	}

	public PassengerLocation(double passLatitude, double passLongitude) {
		this.passLatitude = passLatitude;
		this.passLongitude = passLongitude;
	}

	public double getpassLatitude() {
		return passLatitude;
	}

	public void setpassLatitude(double passLatitude) {
		this.passLatitude = passLatitude;
	}

	public double getpassLongitude() {
		return passLongitude;
	}

	public void setpassLongitude(double passLongitude) {
		this.passLongitude = passLongitude;
	}

		
}
