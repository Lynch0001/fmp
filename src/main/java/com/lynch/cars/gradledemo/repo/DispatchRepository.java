package com.lynch.cars.gradledemo.repo;

import com.lynch.cars.gradledemo.model.Car;
import com.lynch.cars.gradledemo.model.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispatchRepository extends JpaRepository<Dispatch, Long> {

  public Dispatch findDistinctByCarAndDispatchInNull(Car car);

  public List<Dispatch> findDispatchesByCar(Car car);
}
