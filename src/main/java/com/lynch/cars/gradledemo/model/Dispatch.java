package com.lynch.cars.gradledemo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "dispatches")
public class Dispatch implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "out_date", columnDefinition = "DATE")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dispatchOutDate;
  @Column(name = "in_date", columnDefinition = "DATE")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dispatchInDate;
  @Column(name = "dispatch_out")
  private Boolean dispatchOut;
  @Column(name = "dispatch_in")
  private Boolean dispatchIn;
  @Column(name = "out_mileage")
  private Integer dispatchOutMileage;
  @Column(name = "in_mileage")
  private Integer dispatchInMileage;
  @Column(name = "driver")
  private String dispatchDriver;
  @Column(name = "out_tech")
  private String dispatchOutTech;
  @Column(name = "in_tech")
  private String dispatchInTech;
  @Column(name = "out_inspection")
  private Boolean dispatchOutInspection;
  @Column(name = "in_inspection")
  private Boolean dispatchInInspection;

  @ManyToOne
  @JoinColumn(name = "fk_dispatch")
  private Car car;

  public Dispatch() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDispatchOutDate() {
    return dispatchOutDate;
  }

  public void setDispatchOutDate(LocalDate dispatchOutDate) {
    this.dispatchOutDate = dispatchOutDate;
  }

  public LocalDate getDispatchInDate() {
    return dispatchInDate;
  }

  public void setDispatchInDate(LocalDate dispatchInDate) {
    this.dispatchInDate = dispatchInDate;
  }

  public Boolean getDispatchOut() {
    return dispatchOut;
  }

  public void setDispatchOut(Boolean dispatchOut) {
    this.dispatchOut = dispatchOut;
  }

  public Boolean getDispatchIn() {
    return dispatchIn;
  }

  public void setDispatchIn(Boolean dispatchIn) {
    this.dispatchIn = dispatchIn;
  }

  public Integer getDispatchOutMileage() {
    return dispatchOutMileage;
  }

  public void setDispatchOutMileage(Integer dispatchOutMileage) {
    this.dispatchOutMileage = dispatchOutMileage;
  }

  public Integer getDispatchInMileage() {
    return dispatchInMileage;
  }

  public void setDispatchInMileage(Integer dispatchInMileage) {
    this.dispatchInMileage = dispatchInMileage;
  }

  public String getDispatchDriver() {
    return dispatchDriver;
  }

  public void setDispatchDriver(String dispatchDriver) {
    this.dispatchDriver = dispatchDriver;
  }

  public String getDispatchOutTech() {
    return dispatchOutTech;
  }

  public void setDispatchOutTech(String dispatchOutTech) {
    this.dispatchOutTech = dispatchOutTech;
  }

  public String getDispatchInTech() {
    return dispatchInTech;
  }

  public void setDispatchInTech(String dispatchInTech) {
    this.dispatchInTech = dispatchInTech;
  }

  public Boolean getDispatchOutInspection() {
    return dispatchOutInspection;
  }

  public void setDispatchOutInspection(Boolean dispatchOutInspection) {
    this.dispatchOutInspection = dispatchOutInspection;
  }

  public Boolean getDispatchInInspection() {
    return dispatchInInspection;
  }

  public void setDispatchInInspection(Boolean dispatchInInspection) {
    this.dispatchInInspection = dispatchInInspection;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  @Override
  public String toString() {
    return "Dispatch{" +
            "id=" + id +
            ", dispatchOutDate=" + dispatchOutDate +
            ", dispatchInDate=" + dispatchInDate +
            ", dispatchOut=" + dispatchOut +
            ", dispatchIn=" + dispatchIn +
            ", car=" + car +
            '}';
  }
}
