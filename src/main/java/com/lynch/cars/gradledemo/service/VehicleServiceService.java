package com.lynch.cars.gradledemo.service;

import com.lynch.cars.gradledemo.model.Car;
import com.lynch.cars.gradledemo.model.Dispatch;
import com.lynch.cars.gradledemo.model.VehicleService;
import com.lynch.cars.gradledemo.model.VehicleStatus;
import com.lynch.cars.gradledemo.repo.CarRepository;
import com.lynch.cars.gradledemo.repo.VehicleServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceService {

  private static Logger log = LoggerFactory.getLogger(VehicleServiceService.class);

  @Autowired
  private VehicleServiceRepository vehicleServiceRepository;

  @Autowired
  private CarRepository carRepository;

  public VehicleService addService(VehicleService service){
    return vehicleServiceRepository.save(service);
  }

  public List<VehicleService> getServices(){
    return vehicleServiceRepository.findAll();
  }

  public List<VehicleService> getAllServices(){
    return vehicleServiceRepository.findAll();
  }

  public Car updateVehicleWithNewService(Long id, VehicleService vehicleService) throws Exception {
    log.debug("VS SERVICE - Received Vehicle Id: {}", id);
    log.debug("VS SERVICE - Received Service: {}", vehicleService);

    // get current vehicle
    Car car = carRepository.findById(id).get();

    // initialize new maintenance service and save
    VehicleService newService = new VehicleService();
    newService.setServiceIn(true);
    newService.setServiceInDate(vehicleService.getServiceInDate());
    newService.setServiceInMileage(vehicleService.getServiceInMileage());
    newService.setServiceInTech(vehicleService.getServiceInTech());
    vehicleService.setCar(car);
    vehicleServiceRepository.save(vehicleService);

    // update vehicle availability status and mileage
    if(vehicleService.getServiceInMileage() >= car.getMileage()) {
      car.setMileage(vehicleService.getServiceInMileage());
      carRepository.save(car);
    } else {
      throw new Exception("Vehicle Mileage Error - Dispatch Mileage Less than Current Mileage - Update Current Mileage");
    }
    car.setStatus(VehicleStatus.valueOf(VehicleStatus.class, "NOTAVAILABLE_S"));
    carRepository.save(car);
    return car;
  }

  public Boolean vehicleAvailableForService(Long id) throws Exception{
    if (!carRepository.existsCarsById(id)) {
      log.debug("CAR SERVICE - throw not found exception");
      throw new Exception("Vehicle Not Found");
    }
    Car car = carRepository.findById(id).get();
    if (car.getStatus().getLabel().contains("Not Available")) {
      log.debug("CAR SERVICE - throw not available exception");
      throw new Exception("Vehicle Not Available for Service");
    }
    return true;
  }

  public Boolean vehicleIsInService(Long id) throws Exception{
    Car car = carRepository.findById(id).get();
    if (!carRepository.existsCarsById(id)) {
      log.debug("VS SERVICE - throw not found exception");
      throw new Exception("Vehicle Not Found");
    }
    if (!car.getStatus().getLabel().contains("Service")) {
      log.debug("VS SERVICE - throw not available exception");
      throw new Exception("Vehicle is Not in Maintenance Service");
    }
    return true;
  }

  public VehicleService findActiveServiceForVehicle(Car car){
    return vehicleServiceRepository.findDistinctByCarAndServiceOutNull(car);
  }

  public Car updateVehicleWithReturnFromService(Long id, @NonNull VehicleService vehicleService) throws Exception {
    log.debug("VS SERVICE - Received Vehicle Id: {}", id);

    // get current vehicle
    Car car = carRepository.findById(id).get();

    // find active service for car
    VehicleService findVehicleService = findActiveServiceForVehicle(car);
    log.debug("VS SERVICE - Active Vehicle Service Id: {}", findVehicleService.getId());

    // get active service and update with return data
    VehicleService activeVehicleService = vehicleServiceRepository.getOne(findVehicleService.getId());
    activeVehicleService.setServiceOut(true);
    activeVehicleService.setServiceOutDate(vehicleService.getServiceOutDate());
    activeVehicleService.setServiceOutMileage(vehicleService.getServiceOutMileage());
    activeVehicleService.setServiceOutTech(vehicleService.getServiceOutTech());
    activeVehicleService.setServiceType(vehicleService.getServiceType());
    vehicleServiceRepository.save(activeVehicleService);

    // update vehicle availability and mileage

    if(vehicleService.getServiceOutMileage() >= car.getMileage()) {
      car.setMileage(vehicleService.getServiceOutMileage());
      carRepository.save(car);
    } else {
      throw new Exception("Vehicle Mileage Error - Dispatch Mileage Less than Current Mileage - Update Current Mileage");
    }

    car.setStatus(VehicleStatus.valueOf(VehicleStatus.class, "AVAILABLE"));
    carRepository.save(car);
    return car;
  }
}
