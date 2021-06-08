package com.zerofiltre.parkingbot.model;

public class Vehicle {

  private String registrationNumber;
  private String category;

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
        "registrationNumber='" + registrationNumber + '\'' +
        ", category='" + category + '\'' +
        '}';
  }
}
