package com.zerofiltre.parkingbot;

import static com.zerofiltre.parkingbot.model.ParkingTypeEnum.SPOT;

import com.zerofiltre.parkingbot.model.Parking;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.service.ParkingService;

public class ParkingBot {

  static ParkingService parkingService = new ParkingService();

  /**
   * Ceci est la méthode Main
   *
   * @param args : Tableau de données entrées lors du lancement de l'application
   */
  public static void main(String[] args) {
    Parking building = parkingService.initParking();
    listCars(building);

  }

  static void listCars(Parking parking) {
    if (SPOT.equals(parking.getType())) {
      Vehicle vehicle = parking.getVehicle();
      System.out
          .println("Véhicule d'immatriculation: " + vehicle.getRegistrationNumber() + " situé à la place " + vehicle
              .getParkingSpotNumber());
    } else {
      for (Parking subParking : parking.getSubParkings()) {
        listCars(subParking);
      }
    }
  }


}
