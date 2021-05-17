package com.practice.driver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.practice.driver.model.AvailableCabDetails;
import com.practice.driver.model.Driver;
import com.practice.driver.model.DriverLocation;
import com.practice.driver.model.PassengerLocation;
import com.practice.driver.service.DriverService;
import com.practice.driver.util.FieldErrorMessage;
import com.practice.driver.util.Message;

@RestController
@RequestMapping("api/v1")
public class DriverController {

	@Autowired
	private DriverService driverService;

	// Register driver
	@PostMapping("/driver/register")
	public ResponseEntity<Driver> addDriver(@Valid @RequestBody Driver driver) {
		return new ResponseEntity<Driver>(driverService.addDriver(driver), HttpStatus.CREATED);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	List<FieldErrorMessage> fieldErrorMessages(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<FieldErrorMessage> errorMessage = fieldErrors.stream()
				.map(fieldError -> new FieldErrorMessage("failure", fieldError.getDefaultMessage()))
				.collect(Collectors.toList());
		return errorMessage;
	}

	//Create driver location by driver id
	@PostMapping("/driver/{id}/sendLocation")
	public ResponseEntity<Map<String, String>> addLocationToDriver(@PathVariable Long id,
			@RequestBody DriverLocation driverLocation) {
		Map<String, String> map = new HashMap<>();
		Optional<Driver> driver = driverService.getDriverById(id);
		if (driver.isPresent() && driver.get().getLocation() == null) {
			Driver drvr = driver.get();
			drvr.setLocation(driverLocation);
			driverLocation.setDriver(drvr);
			driverService.addDriver(drvr);
			map.put("status", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		map.put("status", "Driver not found or Location for the Driver already exists");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	Message exceptionHandler(ValidationException e) {
		return new Message(e.getMessage());
	}

	//Get all avaliable cars based on passenger location
	@PostMapping("/passenger/available_cabs")
	public ResponseEntity<List<AvailableCabDetails>> findAvailableDrivers(
			@RequestBody PassengerLocation passengerLocation) {
		List<AvailableCabDetails> driverDetails = driverService.availableDrivers(passengerLocation);
		if (!driverDetails.isEmpty()) {
			return new ResponseEntity<List<AvailableCabDetails>>(driverDetails, HttpStatus.OK);
		}
		throw new ValidationException("No available cabs for youo location");
	}
	
	// Update driver location with respect to driverID
	@PutMapping("/driver/{id}/updateDriverLocation")
	public ResponseEntity<Map<String, String>> updateDriverLocation (@PathVariable Long id,@RequestBody DriverLocation driverLocation){
		String[] excludedProperties = {"id", "driver"};
		Map<String, String> map = new HashMap<>();
		Optional<Driver> existingDriver = driverService.getDriverById(id);
		if (existingDriver.isPresent() && existingDriver.get().getLocation() != null) {
			DriverLocation location = existingDriver.get().getLocation();
			BeanUtils.copyProperties(driverLocation, location, excludedProperties );
			existingDriver.get().setLocation(location);
			driverService.addDriver(existingDriver.get());
			map.put("status", "Location Updated Successfully");
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		}

		map.put("status", "Driver not found or Location for the Driver doesn't exists");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}
}
