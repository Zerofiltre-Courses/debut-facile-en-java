package com.zerofiltre.parkingbot.model;

public class Vehicle {

  private String registrationNumber;

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String getCategory() {
    return "VEHICLE";
  }

}
