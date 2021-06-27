package com.zerofiltre.parkingbot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class ParkingServiceTest {

  public static final String REGISTRATION_NUMBER = "AZ-458-56";

  ParkingService parkingService = new ParkingService();


  @Test
  void givenAVehicle_processIncomingVehicle_generatesTicketWithRightTime() {
    //given : Soit un véhicule a l'entrée du parking avec une plaque d'immatriculation X
    Vehicle vehicle = new Vehicle();
    vehicle.setRegistrationNumber(REGISTRATION_NUMBER);
    Date now = new Date();

    //when : Enregistrer le véhicule
    Ticket ticket = parkingService.processIncomingVehicle(vehicle);

    //then:
    //génère un ticket,
    assertThat(ticket).isNotNull();

    //associé au véhicule de plaque x,
    Vehicle registeredVehicle = ticket.getVehicle();
    assertThat(registeredVehicle).isNotNull();
    String registeredRegistrationNumber = registeredVehicle.getRegistrationNumber();
    assertThat(registeredRegistrationNumber).isEqualTo(REGISTRATION_NUMBER);

    //avec comme date d'entrée la date actuelle à 30 secondes près
    Date date = ticket.getEnteringTime();
    assertThat(date).isNotNull();
    Date nowPlus30Seconds = new Date(now.getTime() + 30 * 1000);
    assertThat(date).isBeforeOrEqualTo(nowPlus30Seconds);
  }


}
