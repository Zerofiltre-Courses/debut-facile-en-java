package com.zerofiltre.parkingbot.model;

public class Vehicle {

  private String registrationNumber;

  private String parkingSpotNumber;

  private VehiculeCategoryEnum category;

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public VehiculeCategoryEnum getCategory() {
    return VehiculeCategoryEnum.VEHICULE;
  }

  public String getParkingSpotNumber() {
    return parkingSpotNumber;
  }

  public void setParkingSpotNumber(String parkingSpotNumber) {
    this.parkingSpotNumber = parkingSpotNumber;
  }
}
