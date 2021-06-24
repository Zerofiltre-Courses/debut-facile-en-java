package com.zerofiltre.parkingbot.model;

public class Car extends Vehicle {

  @Override
  public VehiculeCategoryEnum getCategory() {
    return VehiculeCategoryEnum.CITADINE;
  }
}
