package com.lynch.cars.gradledemo.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "drivers")
public class Driver implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long driverId;

  private String name;

  public Driver() {
  }

  public Long getDriverId() {
    return driverId;
  }

  public void setDriverId(Long driverId) {
    this.driverId = driverId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Driver{" +
            "driverId=" + driverId +
            ", name='" + name + '\'' +
            '}';
  }
}
