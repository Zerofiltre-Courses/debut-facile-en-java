package com.zerofiltre.parkingbot;

public class ParkingBot {

  /**
   * Ceci est la méthode Main
   *
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
    String welcomeSentence = "Hello, recevez la bienvenue ";
    System.out.println(welcomeSentence);
  }

  private static void sayBye() {
    System.out.println("Recevez les aurevoirs");
  }
}
