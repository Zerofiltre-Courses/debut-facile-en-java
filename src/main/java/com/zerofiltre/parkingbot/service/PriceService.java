package com.zerofiltre.parkingbot.service;

import static com.zerofiltre.parkingbot.model.VehiculeCategoryEnum.BICYCLE;
import static com.zerofiltre.parkingbot.model.VehiculeCategoryEnum.CITADINE;

import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.model.VehiculeCategoryEnum;

public class PriceService {

  public double getExitPrice(Ticket ticket) {
    Vehicle vehicle = ticket.getVehicle();
    VehiculeCategoryEnum category = vehicle.getCategory();

    double pricePerMinForCitadine = 0.08;
    double pricePerMinFor2Roues = 0.02;
    double defaultPricePerMin = 0.05;
    long durationInMinutes = (ticket.getExitTime().getTime() - ticket.getEnteringTime().getTime()) / (60 * 1000);

    double finalPrice;

    if (CITADINE.equals(category)) {
      finalPrice = durationInMinutes * pricePerMinForCitadine;
    } else if (BICYCLE.equals(category)) {
      finalPrice = durationInMinutes * pricePerMinFor2Roues;
    } else {
      finalPrice = durationInMinutes * defaultPricePerMin;
    }
    return finalPrice;
  }
}
