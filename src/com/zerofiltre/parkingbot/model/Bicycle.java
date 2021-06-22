package com.zerofiltre.parkingbot.model;

import static com.zerofiltre.parkingbot.model.VehiculeCategoryEnum.BICYCLE;

public class Bicycle extends Vehicle {

  @Override
  public VehiculeCategoryEnum getCategory() {
    return BICYCLE;
  }

}
