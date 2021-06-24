package com.zerofiltre.parkingbot.service.service;

import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import java.util.Date;

public class ParkingService {

  public Ticket processIncomingVehicle(Vehicle vehicle) {
    Ticket ticket = new Ticket();
    ticket.setVehicle(vehicle);
    ticket.setEnteringTime(new Date());
    return ticket;
  }
}
