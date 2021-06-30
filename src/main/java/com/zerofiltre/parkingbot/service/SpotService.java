package com.zerofiltre.parkingbot.service;

import com.zerofiltre.parkingbot.model.Parking;

public class SpotService {

  boolean spotsAvailable(Parking parking) {
    return parking.getFreeSpots() > 0;
  }

}
