package com.lynch.cars.gradledemo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "services")
public class VehicleService implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "out_date", columnDefinition = "DATE")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate serviceOutDate;
  @Column(name = "in_date", columnDefinition = "DATE")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate serviceInDate;

  @Column(name = "service_out")
  private Boolean serviceOut;
  @Column(name = "service_in")
  private Boolean serviceIn;
  @Column(name = "out_mileage")
  private Integer serviceOutMileage;
  @Column(name = "in_mileage")
  private Integer serviceInMileage;
  @Column(name = "out_tech")
  private String serviceOutTech;
  @Column(name = "in_tech")
  private String serviceInTech;
  @Column(name = "type")
  private String serviceType;

  @ManyToOne
  @JoinColumn(name = "fk_service")
  private Car car;

  public VehicleService() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getServiceOutDate() {
    return serviceOutDate;
  }

  public void setServiceOutDate(LocalDate serviceOutDate) {
    this.serviceOutDate = serviceOutDate;
  }

  public LocalDate getServiceInDate() {
    return serviceInDate;
  }

  public void setServiceInDate(LocalDate serviceInDate) {
    this.serviceInDate = serviceInDate;
  }

  public Boolean getServiceOut() {
    return serviceOut;
  }

  public void setServiceOut(Boolean serviceOut) {
    this.serviceOut = serviceOut;
  }

  public Boolean getServiceIn() {
    return serviceIn;
  }

  public void setServiceIn(Boolean serviceIn) {
    this.serviceIn = serviceIn;
  }

  public Integer getServiceOutMileage() {
    return serviceOutMileage;
  }

  public void setServiceOutMileage(Integer serviceOutMileage) {
    this.serviceOutMileage = serviceOutMileage;
  }

  public Integer getServiceInMileage() {
    return serviceInMileage;
  }

  public void setServiceInMileage(Integer serviceInMileage) {
    this.serviceInMileage = serviceInMileage;
  }

  public String getServiceOutTech() {
    return serviceOutTech;
  }

  public void setServiceOutTech(String serviceOutTech) {
    this.serviceOutTech = serviceOutTech;
  }

  public String getServiceInTech() {
    return serviceInTech;
  }

  public void setServiceInTech(String serviceInTech) {
    this.serviceInTech = serviceInTech;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  @Override
  public String toString() {
    return "VehicleService{" +
            "id=" + id +
            ", serviceOutDate=" + serviceOutDate +
            ", serviceInDate=" + serviceInDate +
            ", serviceOut=" + serviceOut +
            ", serviceIn=" + serviceIn +
            ", serviceOutMileage=" + serviceOutMileage +
            ", serviceInMileage=" + serviceInMileage +
            ", serviceOutTech='" + serviceOutTech + '\'' +
            ", serviceInTech='" + serviceInTech + '\'' +
            ", serviceType='" + serviceType + '\'' +
            ", car=" + car +
            '}';
  }
}
