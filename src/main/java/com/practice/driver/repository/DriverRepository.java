package com.practice.driver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.driver.model.Driver;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {

}
