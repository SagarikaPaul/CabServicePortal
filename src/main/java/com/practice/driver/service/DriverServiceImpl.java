package com.practice.driver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.driver.model.AvailableCabDetails;
import com.practice.driver.model.Driver;
import com.practice.driver.model.PassengerLocation;
import com.practice.driver.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	public Driver addDriver(Driver driver) {
		return driverRepository.save(driver);
	}

	@Override
	public Optional<Driver> getDriverById(Long id) {
		return driverRepository.findById(id);
	}

	@Override
	public List<AvailableCabDetails> availableDrivers(PassengerLocation passengerLocation) {
		List<AvailableCabDetails> listOfAvailableDrivers = new ArrayList<>();
		Iterable<Driver> allDrivers = driverRepository.findAll();
		List<Driver> driverList = new ArrayList<>(10);
		allDrivers.forEach(driverList::add);

		for (Driver driver : driverList) {
			AvailableCabDetails availableCabDetails = new AvailableCabDetails();
			double distLatitude = Math
					.toRadians(driver.getLocation().getLatitude() - passengerLocation.getpassLatitude());
			double distLongitude = Math
					.toRadians(driver.getLocation().getLongitude() - passengerLocation.getpassLongitude());

			double passengerLatitude = Math.toRadians(passengerLocation.getpassLatitude());
			double driverLatitude = Math.toRadians(driver.getLocation().getLatitude());

			double a = Math.pow(Math.sin(distLatitude / 2), 2)
					+ Math.pow(Math.sin(distLongitude / 2), 2) * Math.cos(passengerLatitude) * Math.cos(driverLatitude);
			double rad = 6371;
			double c = 2 * Math.asin(Math.sqrt(a));
			double distance = rad * c;
			if (distance <= 4) {
				availableCabDetails.setName(driver.getName());
				availableCabDetails.setPhoneNumber(driver.getPhoneNumber());
				availableCabDetails.setCarNumber(driver.getCarNumber());
				listOfAvailableDrivers.add(availableCabDetails);
			}
		}
		return listOfAvailableDrivers;
	}

}
