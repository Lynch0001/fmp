package com.lynch.cars.gradledemo.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "techs")
public class MaintTech implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long techId;

  private String name;

  public MaintTech(){}

  public Long getTechId() {
    return techId;
  }

  public void setTechId(Long techId) {
    this.techId = techId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "MaintTech{" +
            "techId=" + techId +
            ", name='" + name + '\'' +
            '}';
  }
}
