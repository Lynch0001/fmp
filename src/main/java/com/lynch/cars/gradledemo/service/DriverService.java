package com.lynch.cars.gradledemo.service;

import com.lynch.cars.gradledemo.model.Driver;
import com.lynch.cars.gradledemo.repo.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

  @Autowired
  private DriverRepository driverRepository;

  public List<Driver> getDrivers(){
    return driverRepository.findAll();
  }

}
