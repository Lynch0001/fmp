package com.lynch.cars.gradledemo.service;

import com.lynch.cars.gradledemo.model.Car;
import com.lynch.cars.gradledemo.model.Dispatch;
import com.lynch.cars.gradledemo.model.VehicleStatus;
import com.lynch.cars.gradledemo.repo.CarRepository;
import com.lynch.cars.gradledemo.repo.DispatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispatchService {

  private static Logger log = LoggerFactory.getLogger(DispatchService.class);

  @Autowired
  private DispatchRepository dispatchRepository;

  @Autowired
  private CarRepository carRepository;

  public Dispatch addDispatch(Dispatch dispatch){
    return dispatchRepository.save(dispatch);
  }

  public Dispatch updateDispatch(Dispatch dispatch){
    return dispatchRepository.save(dispatch);
  }


  public List<Dispatch> getDispatches(){
        return dispatchRepository.findAll();
  }

  public List<Dispatch> getAllDispatches(){
        return dispatchRepository.findAll();
  }


  public Boolean vehicleAvailableForDispatch(Long id) throws Exception{
      if (!carRepository.existsCarsById(id)) {
      log.debug("CAR SERVICE - throw not found exception");
      throw new Exception("Vehicle Not Found");
    }
    Car car = carRepository.findById(id).get();
    if (car.getStatus().getLabel().contains("Not Available")) {
      log.debug("CAR SERVICE - throw not available exception");
      throw new Exception("Vehicle Not Available for Dispatch");
    }
    return true;
  }

  public Boolean vehicleIsDispatched(Long id) throws Exception{
    Car car = carRepository.findById(id).get();
    if (!carRepository.existsCarsById(id)) {
      log.debug("CAR SERVICE - throw not found exception");
      throw new Exception("Vehicle Not Found");
    }
    if (!car.getStatus().getLabel().contains("Dispatched")) {
      log.debug("CAR SERVICE - throw not available exception");
      throw new Exception("Vehicle is Not Dispatched");
    }
    return true;
  }

  public Dispatch findActiveDispatchForVehicle(Car car){
    return dispatchRepository.findDistinctByCarAndDispatchInNull(car);
  }


  public Car updateVehicleWithNewDispatch(Long id, Dispatch dispatch){
    log.debug("CAR SERVICE - Received Vehicle Id: {}", id);
    log.debug("CAR SERVICE - Received Dispatch: {}", dispatch);

    // get current vehicle
    Car car = carRepository.findById(id).get();

    // initialize new dispatch and save
    Dispatch newDispatch = new Dispatch();
    newDispatch.setDispatchOut(true);
    newDispatch.setDispatchOutInspection(true);
    newDispatch.setDispatchOutDate(dispatch.getDispatchOutDate());
    newDispatch.setDispatchOutMileage(dispatch.getDispatchOutMileage());
    newDispatch.setDispatchOutTech(dispatch.getDispatchOutTech());
    newDispatch.setDispatchDriver(dispatch.getDispatchDriver());
    newDispatch.setCar(car);
    dispatchRepository.save(newDispatch);

    // update vehicle availability status
    car.setStatus(VehicleStatus.valueOf(VehicleStatus.class, "NOTAVAILABLE_D"));
    carRepository.save(car);
    return car;
  }



  public Car updateVehicleWithReturnDispatch(Long id, @NonNull Dispatch dispatch){
    log.debug("CAR SERVICE - Received Vehicle Id: {}", id);

    // get current vehicle
    Car car = carRepository.findById(id).get();

    // find active dispatch for car
    Dispatch findDispatch = findActiveDispatchForVehicle(car);
    log.debug("CAR SERVICE - Active Dispatch Id: {}", findDispatch.getId());

    // get active dispatch and update with return data
    Dispatch activeDispatch = dispatchRepository.getOne(findDispatch.getId());
    activeDispatch.setDispatchIn(true);
    activeDispatch.setDispatchInInspection(true);
    activeDispatch.setDispatchInDate(dispatch.getDispatchInDate());
    activeDispatch.setDispatchInMileage(dispatch.getDispatchInMileage());
    activeDispatch.setDispatchInTech(dispatch.getDispatchInTech());
    dispatchRepository.save(activeDispatch);

    // update vehicle availability
    car.setStatus(VehicleStatus.valueOf(VehicleStatus.class, "AVAILABLE"));
    carRepository.save(car);
    return car;
  }

}
