package com.practice.driver.service;

import java.util.List;
import java.util.Optional;

import com.practice.driver.model.AvailableCabDetails;
import com.practice.driver.model.Driver;
import com.practice.driver.model.PassengerLocation;

public interface DriverService {
	public Driver addDriver(Driver driver);
	public Optional<Driver> getDriverById(Long id);
	public List<AvailableCabDetails> availableDrivers(PassengerLocation passengerLocation);
}
