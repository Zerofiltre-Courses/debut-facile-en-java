package com.zerofiltre.parkingbot;

import com.zerofiltre.parkingbot.model.Bicycle;
import com.zerofiltre.parkingbot.model.Car;
import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import com.zerofiltre.parkingbot.service.ParkingService;
import com.zerofiltre.parkingbot.util.Printer;
import java.util.ArrayList;
import java.util.List;

public class ParkingBot {

  static ParkingService parkingService = new ParkingService();
  static Printer printer;

  /**
   * Ceci est la méthode Main
   *
   * @param args : Tableau de données entrées lors du lancement de l'application
   */
  public static void main(String[] args) {

    printer = new Printer() {
      @Override
      public void print(Object o) {
        System.out.println(o);
      }
    };

    processVehicles();
  }

  private static void processVehicles() {
    List<Ticket> tickets = new ArrayList<>();

    Vehicle vehicle = new Vehicle();
    vehicle.setRegistrationNumber("LS-324-PM");
    Ticket vehicleTicket = parkingService.processIncomingVehicle(vehicle);
    printer.print(vehicleTicket);
    tickets.add(vehicleTicket);

    Vehicle bicycle = new Bicycle();
    bicycle.setRegistrationNumber("PM-254-OP");
    Ticket bicycleTicket = parkingService.processIncomingVehicle(bicycle);
    printer.print(bicycleTicket);
    tickets.add(bicycleTicket);

    Vehicle car = new Car();
    bicycle.setRegistrationNumber("BX-256-QX");
    Ticket carTicket = parkingService.processIncomingVehicle(car);
    printer.print(carTicket);
    tickets.add(carTicket);

    System.out.println("Début du traitement de sorties en lot de " + tickets.size() + " véhicules");
    for (int i = 0; i < tickets.size(); i++) {
      try {
        printer.print(parkingService.processExitingVehicle(tickets.get(i)));
      } catch (Exception e) {
        printer.print("Une erreur est survenue lors de la sortie d'un ou plusieurs véhicules");
      }
    }
    printer.print("Fin du traitement des sorties par lot");


  }


}
