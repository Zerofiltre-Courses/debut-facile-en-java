package com.zerofiltre.parkingbot.service;

import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import java.util.Calendar;
import java.util.Date;

public class ParkingService {

  public Ticket processIncomingVehicle(Vehicle vehicle) {
    Ticket ticket = new Ticket();
    Date now = new Date();
    ticket.setEnteringTime(now);
    ticket.setVehicle(vehicle);
    return ticket;
  }

  public Ticket processExitingVehicle(Ticket ticket) {
    long now = new Date().getTime();
    now += 60 * 60 * 1000;

    Date exitTime = new Date(now);
    ticket.setExitTime(exitTime);

    Vehicle vehicle = ticket.getVehicle();
    String category = vehicle.getCategory();

    double pricePerMinForCitadine = 0.08;
    double pricePerMinFor2Roues = 0.02;
    double defaultPricePerMin = 0.05;
    long durationInMinutes = (ticket.getExitTime().getTime() - ticket.getEnteringTime().getTime()) / (60 * 1000);

    double finalPrice = 0;

    if ("CITADINE".equals(category)) {
      finalPrice = durationInMinutes * pricePerMinForCitadine;
    } else if ("2 ROUES".equals(category)) {
      finalPrice = durationInMinutes * pricePerMinFor2Roues;
    } else {
      finalPrice = durationInMinutes * defaultPricePerMin;
    }
    ticket.setAmount(finalPrice);

    return ticket;

  }


}
