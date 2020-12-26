package com.lynch.cars.gradledemo.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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

  @DateTimeFormat(pattern = "yyyy-MM-dd") //"yyyy-MM-dd'T'HH:mm"
  @Column(name = "lastservice")
  private LocalDate lastservice;

  @Column(name = "division")
  private String division;





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
