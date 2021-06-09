package com.zerofiltre.parkingbot.service;

import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import java.util.Date;

public class ParkingService {

  public Ticket processIncomingVehicle(Vehicle vehicle){
    Ticket ticket = new Ticket();
    Date now = new Date();
    ticket.setEnteringTime(now);
    ticket.setVehicle(vehicle);
    return ticket;
  }

}
