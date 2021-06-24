package com.zerofiltre.parkingbot.model;

public class Bicycle extends Vehicle {

  @Override
  public VehiculeCategoryEnum getCategory() {
    return VehiculeCategoryEnum.BICYCLE;
  }

}
