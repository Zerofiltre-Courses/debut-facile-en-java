package com.zerofiltre.parkingbot;

import java.security.SecureRandom;

public class ParkingBot {

  static String[] customers =
      {"Leye", "Ali", "Réné", "Booba", "Léa", "Hamza", "Philippe", "Zineb", "Jules", "Mukul", "Will"};

  static int parkingSize = new SecureRandom().nextInt(10);

  /**
   * Ceci est la méthode Main
   *
   * @param args : Tableau de données entrées lors du lancement de l'application
   */
  public static void main(String[] args) {

  }


  /**
   *
   * @param customer Le client à qui dire Hello
   */
  private static void sayHello(String customer) {
    String welcomeSentence = "Hello " + customer;
    System.out.println(welcomeSentence);
  }

}
