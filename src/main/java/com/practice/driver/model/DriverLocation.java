package com.practice.driver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class DriverLocation {
	
	@Id
	@GeneratedValue
	private Long id;
	private double longitude;
	private double latitude;
	
	@OneToOne
	@JoinColumn(name = "driver_id", referencedColumnName = "id")
	private Driver driver;

	public DriverLocation() {
	
	}

	public DriverLocation(Long id, double longitude, double latitude, Driver driver) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.driver=driver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	
	
}
