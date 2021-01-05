package com.lynch.cars.gradledemo.repo;

import com.lynch.cars.gradledemo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  @Query("select c from Car c where c.lastservice < ?1")
  public List<Car> getServiceOutOfDate(LocalDate expiredAsOfDate);

  @Query("select c from Car c where c.lastservice > ?1 and c.lastservice < ?2")
  public List<Car> getPendingServiceVehicleList(LocalDate pendingRangeStartDate, LocalDate pendingRangeEndDate);

  @Query("select c from Car c where c.status in " +
          "(com.lynch.cars.gradledemo.model.VehicleStatus.NOTAVAILABLE_S, " +
          "com.lynch.cars.gradledemo.model.VehicleStatus.NOTAVAILABLE_D," +
          "com.lynch.cars.gradledemo.model.VehicleStatus.NOTAVAILABLE_M," +
          "com.lynch.cars.gradledemo.model.VehicleStatus.NOTAVAILABLE_P," +
          "com.lynch.cars.gradledemo.model.VehicleStatus.NOTAVAILABLE_X," +
          "com.lynch.cars.gradledemo.model.VehicleStatus.NOTAVAILABLE_A)")
  public List<Car> getVehiclesNotAvailable();

  @Query("select c from Car c where c.status = com.lynch.cars.gradledemo.model.VehicleStatus.NOTAVAILABLE_D")
  public List<Car> getDispatchedVehicles();

  @Query("select c from Car c where c.status = com.lynch.cars.gradledemo.model.VehicleStatus.AVAILABLE")
  public List<Car> getAllAvailableVehicles();

  public Boolean existsCarsById(Long id);
}
