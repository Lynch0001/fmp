package com.lynch.cars.gradledemo.repo;

import com.lynch.cars.gradledemo.model.MaintTech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintTechRepository extends JpaRepository<MaintTech, Long> {
}
