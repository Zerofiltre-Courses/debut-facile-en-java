package com.zerofiltre.parkingbot.service;

import static com.zerofiltre.parkingbot.model.VehiculeCategoryEnum.BICYCLE;
import static com.zerofiltre.parkingbot.model.VehiculeCategoryEnum.CITADINE;

import com.zerofiltre.parkingbot.model.Parking;
import com.zerofiltre.parkingbot.model.ParkingTypeEnum;
import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.model.VehiculeCategoryEnum;
import java.security.SecureRandom;
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
    VehiculeCategoryEnum category = vehicle.getCategory();

    double pricePerMinForCitadine = 0.08;
    double pricePerMinFor2Roues = 0.02;
    double defaultPricePerMin = 0.05;
    long durationInMinutes = (ticket.getExitTime().getTime() - ticket.getEnteringTime().getTime()) / (60 * 1000);

    double finalPrice = 0;

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

  public Parking initParking() {
    Parking building = new Parking();
    building.setType(ParkingTypeEnum.BUILDING);
    building.setNumber(1);
    for (int i = 0; i < 50; i++) {
      Parking floor = new Parking();
      floor.setType(ParkingTypeEnum.FLOOR);
      floor.setNumber(i);

      for (int j = 1; j <= 30; j++) {
        Parking hall = new Parking();
        hall.setType(ParkingTypeEnum.HALL);
        hall.setNumber(j);
        for (int k = 1; k <= 20; k++) {
          Parking spot = new Parking();
          spot.setType(ParkingTypeEnum.SPOT);
          spot.setNumber(k);
          Vehicle vehicle = new Vehicle();
          vehicle.setRegistrationNumber(String.valueOf(new SecureRandom().nextInt(10000)));
          vehicle.setParkingSpotNumber("");
          vehicle.setParkingSpotNumber("1-" + i + "-" + j + "-" + k);
          spot.setVehicle(vehicle);
          hall.getSubParkings().add(spot);
        }
        floor.getSubParkings().add(hall);
      }
      building.getSubParkings().add(floor);
    }
    return building;
  }
}
