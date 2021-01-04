package com.lynch.cars.gradledemo.repo;

import com.lynch.cars.gradledemo.model.Car;
import com.lynch.cars.gradledemo.model.VehicleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleServiceRepository extends JpaRepository<VehicleService, Long> {

  public List<VehicleService> getVehicleServicesByCar(Car car);

  public VehicleService getVehicleServiceByCarAndId(Car car, Long id);

  public VehicleService findDistinctByCarAndServiceOutNull(Car car);
}
