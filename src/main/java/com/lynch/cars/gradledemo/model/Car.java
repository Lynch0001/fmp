package com.lynch.cars.gradledemo.model;


import com.lynch.cars.gradledemo.service.VehicleServiceService;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "make")
  private String make;

  @Column(name = "model")
  private String model;

  @Column(name = "manufactureyear")
  private Integer manufactureYear;

  @Column(name = "color")
  private String color;

  @Column(name = "vin")
  private String vin;

  @Column(name = "bumper")
  private String bumper;

  @Column(name = "mileage")
  private Integer mileage;

  @DateTimeFormat(pattern = "yyyy-MM-dd") //"yyyy-MM-dd'T'HH:mm"
  @Column(name = "lastservice")
  private LocalDate lastservice;

  @Column(name = "division")
  private String division;

  @Column(name = "current_status")
  @Enumerated(EnumType.STRING)
  private VehicleStatus status = VehicleStatus.NOTAVAILABLE_A;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "car", orphanRemoval = true)
  private List<Dispatch> dispatches = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "car", orphanRemoval = true)
  private List<VehicleService> services = new ArrayList<>();

  public Car(){}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getManufactureYear() {
    return manufactureYear;
  }

  public void setManufactureYear(Integer manufactureYear) {
    this.manufactureYear = manufactureYear;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getBumper() {
    return bumper;
  }

  public void setBumper(String bumper) {
    this.bumper = bumper;
  }

  public Integer getMileage() {
    return mileage;
  }

  public void setMileage(Integer mileage) {
    this.mileage = mileage;
  }

  public LocalDate getLastservice() {
    return lastservice;
  }

  public void setLastservice(LocalDate lastservice) {
    this.lastservice = lastservice;
  }

  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public VehicleStatus getStatus() {
    return status;
  }

  public void setStatus(VehicleStatus status) {
    this.status = status;
  }

  public List<Dispatch> getDispatches() {
    return dispatches;
  }

  public void setDispatches(List<Dispatch> dispatches) {
    this.dispatches = dispatches;
  }

  public List<VehicleService> getServices() {
    return services;
  }

  public void setServices(List<VehicleService> services) {
    this.services = services;
  }

  @Override
  public String toString() {
    return "Car{" +
            "id=" + id +
            ", make='" + make + '\'' +
            ", model='" + model + '\'' +
            ", manufactureYear=" + manufactureYear +
            ", vin='" + vin + '\'' +
            ", bumper='" + bumper + '\'' +
            ", division='" + division + '\'' +
            '}';
  }
}
