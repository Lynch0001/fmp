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

  public Car updateCar(Long id, Car car) throws Exception {
    log.debug("Car Edit for Car: {}", car);
    Car updateCar = carRepository.findById(id).orElseThrow(()-> new Exception("Vehicle Not Found"));
    if(!car.getMake().equals(updateCar.getMake())){updateCar.setMake(car.getMake());}
    if(!car.getModel().equals(updateCar.getModel())){updateCar.setModel(car.getModel());}
    if(!car.getManufactureYear().equals(updateCar.getManufactureYear())){updateCar.setManufactureYear(car.getManufactureYear());}
    if(!car.getBumper().equals(updateCar.getBumper())){updateCar.setBumper(car.getBumper());}
    if(!car.getDivision().equals(updateCar.getDivision())){updateCar.setDivision(car.getDivision());}
    if(!car.getVin().equals(updateCar.getVin())){updateCar.setVin(car.getVin());}
    if(!car.getColor().equals(updateCar.getColor())){updateCar.setColor(car.getColor());}
    if(!car.getMileage().equals(updateCar.getMileage())){updateCar.setMileage(car.getMileage());}
    if(!car.getLastservice().equals(updateCar.getLastservice())){updateCar.setLastservice(car.getLastservice());}
    log.debug("Car Status: {}", updateCar.getStatus());
    log.debug("Update Car Status: {}", car.getStatus());
    if(!car.getStatus().equals(updateCar.getStatus())){updateCar.setStatus(car.getStatus());}
    carRepository.save(updateCar);
    return carRepository.findById(id).orElseThrow(()-> new Exception("Vehicle Not Found"));
  }

  public void deleteCar(Long id){
    carRepository.deleteById(id);
  }

  public List<Car> getServiceOutOfDate(){
    LocalDate expiredAsOfDate = LocalDate.now().minusDays(365);
    return carRepository.getServiceOutOfDate(expiredAsOfDate);
  }

  public List<Car> getPendingServiceVehicleList(){
    LocalDate pendingRangeStartDate = LocalDate.now().minusDays(365);
    LocalDate pendingRangeEndDate = LocalDate.now().minusDays(275);
    return carRepository.getPendingServiceVehicleList(pendingRangeStartDate, pendingRangeEndDate);
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
