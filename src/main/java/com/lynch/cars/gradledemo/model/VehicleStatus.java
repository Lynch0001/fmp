package com.lynch.cars.gradledemo.model;

public enum VehicleStatus {

  AVAILABLE("Available"),
  NOTAVAILABLE_M("Not Available - Maintenance"),
  NOTAVAILABLE_S("Not Available - Service"),
  NOTAVAILABLE_P("Not Available - Parts"),
  NOTAVAILABLE_D("Not Available - Dispatched"),
  NOTAVAILABLE_X("Not Available - Pending Transfer"),
  NOTAVAILABLE_A("Not Available - Pending Acceptance");

  public final String label;

  private VehicleStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
