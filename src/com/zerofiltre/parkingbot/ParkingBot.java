package com.zerofiltre.parkingbot;

import com.zerofiltre.parkingbot.model.Bicycle;
import com.zerofiltre.parkingbot.model.Car;
import com.zerofiltre.parkingbot.model.Ticket;
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
    processVehicles();
  }

  private static void processVehicles() {

    Vehicle vehicle = new Vehicle();
    vehicle.setRegistrationNumber("LS-324-PM");
    Ticket vehicleTicket = parkingService.processIncomingVehicle(vehicle);
    System.out.println(vehicleTicket);

    Vehicle bicycle = new Bicycle();
    bicycle.setRegistrationNumber("PM-254-OP");
    Ticket bicycleTicket = parkingService.processIncomingVehicle(bicycle);
    System.out.println(bicycleTicket);

    Vehicle car = new Car();
    bicycle.setRegistrationNumber("BX-256-QX");
    Ticket carTicket = parkingService.processIncomingVehicle(car);
    System.out.println(carTicket);

  }


}
