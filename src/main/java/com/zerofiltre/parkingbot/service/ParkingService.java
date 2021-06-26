package com.zerofiltre.parkingbot.service;

import static com.zerofiltre.parkingbot.model.VehiculeCategoryEnum.BICYCLE;
import static com.zerofiltre.parkingbot.model.VehiculeCategoryEnum.CITADINE;

import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.model.VehiculeCategoryEnum;
import java.util.Date;

public class ParkingService {

  public Ticket processIncomingVehicle(Vehicle vehicle) {
    Ticket ticket = new Ticket();
    ticket.setVehicle(vehicle);
    ticket.setEnteringTime(new Date());
    return ticket;
  }

  public Ticket processExitingVehicle(Ticket ticket) {

    ticket.setExitTime(new Date());
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
    ticket.setAmount(finalPrice);
    return ticket;
  }
}
