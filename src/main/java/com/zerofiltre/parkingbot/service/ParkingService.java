package com.zerofiltre.parkingbot.service;

import com.zerofiltre.parkingbot.error.NoMoreSpotException;
import com.zerofiltre.parkingbot.model.Parking;
import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import java.util.Date;

public class ParkingService {

  SpotService spotService ;
  PriceService priceService ;

  public ParkingService(SpotService spotService, PriceService priceService) {
    this.spotService = spotService;
    this.priceService = priceService;
  }

  public Ticket processIncomingVehicle(Vehicle vehicle, Parking parking) throws NoMoreSpotException {
    if (spotService.spotsAvailable(parking)) {
      Ticket ticket = new Ticket();
      ticket.setVehicle(vehicle);
      ticket.setEnteringTime(new Date());
      return ticket;
    }
    throw new NoMoreSpotException();
  }

  public Ticket processExitingVehicle(Ticket ticket) {
    ticket.setExitTime(new Date());
    ticket.setAmount(priceService.getExitPrice(ticket));
    return ticket;
  }
}
