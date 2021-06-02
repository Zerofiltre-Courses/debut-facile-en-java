package com.zerofiltre.parkingbot;

import com.zerofiltre.parkingbot.service.ParkingService;

public class ParkingBot {

  static String completion = "des équipes parking Zerofiltre!";
  static ParkingService parkingService = new ParkingService();

  /**
   * Ceci est la méthode Main
   * @param args : Tableau de données entrées lors du lancement de l'application
   */
  public static void main(String[] args) {
    sayHello();
    sayBye();
  }


  /**
   * Cette méthode permet de dire Hello
   */
  private static void sayHello() {
    String welcomeSentence = "Hello, recevez la bienvenue " + completion;
    System.out.println(welcomeSentence);
    String parkService = parkingService.parking;
    String washingService = parkingService.washing;

    System.out.println("Nous proposons les services suivants: "+parkService+" et "+washingService);
  }

  private static void sayBye(){
    System.out.println("Aurevoir et à bientôt "+ completion);
  }
}
