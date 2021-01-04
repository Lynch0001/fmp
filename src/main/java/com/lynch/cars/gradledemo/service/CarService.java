package com.lynch.cars.gradledemo.service;

import com.lynch.cars.gradledemo.model.Car;
import com.lynch.cars.gradledemo.repo.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class CarService {

  private static Logger log = LoggerFactory.getLogger(CarService.class);

  @Autowired
  private CarRepository carRepository;

  public Boolean existsCarsById(Long id){
    return carRepository.existsCarsById(id);
  }

  public List<Car> getCars(){
    return carRepository.findAll();
  }

  public Optional<Car> getCar(Long id){
    log.debug("Service Getting Car - is present: {}", carRepository.findById(id).isPresent());
    return carRepository.findById(id);
  }

  public Long saveCar(Car car){
    carRepository.save(car);
    return car.getId();
  }

  public void editCar(Car car){
    carRepository.save(car);
  }

  public void deleteCar(Long id){
    carRepository.deleteById(id);
  }

  public List<Car> getServiceOutOfDate(){
    LocalDate expiredAsOfDate = LocalDate.now().minusDays(365);
    return carRepository.getServiceOutOfDate(expiredAsOfDate);
  }

  public List<Car> getAllAvailableVehicles(){
    return carRepository.getAllAvailableVehicles();
  }

  public List<Car> getNonAvailableVehicles(){
    return carRepository.getVehiclesNotAvailable();
  }

  public List<Car> getAllDispatchedVehicles(){
    List<Car> cars = carRepository.getDispatchedVehicles();
    return cars;
  }

}
