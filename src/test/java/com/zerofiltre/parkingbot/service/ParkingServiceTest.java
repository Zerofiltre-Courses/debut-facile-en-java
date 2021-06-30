package com.zerofiltre.parkingbot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.zerofiltre.parkingbot.error.NoMoreSpotException;
import com.zerofiltre.parkingbot.model.Bicycle;
import com.zerofiltre.parkingbot.model.Car;
import com.zerofiltre.parkingbot.model.Parking;
import com.zerofiltre.parkingbot.model.Ticket;
import com.zerofiltre.parkingbot.model.Vehicle;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ParkingServiceTest {

  public static final String REGISTRATION_NUMBER = "AZ-458-56";
  public static final String CAR_REGISTRATION_NUMBER = "AZ-JOL-59";
  public static final String BICYCLE_REGISTRATION_NUMBER = "AZ-JOP-89";

  ParkingService parkingService;
  private Vehicle vehicle;
  private Ticket ticket;
  private Date enteringTime;
  private Parking parking;

  SpotService spotService;
  PriceService priceService;


  @BeforeAll
  void init() {
    // Soit un véhicule a l'entrée du parking avec une plaque d'immatriculation X
    vehicle = new Vehicle();
    vehicle.setRegistrationNumber(REGISTRATION_NUMBER);
    ticket = new Ticket();
    ticket.setVehicle(vehicle);

    //given a parking with some free spot
    parking = new Parking();
    spotService = spy(SpotService.class);
    priceService = spy(PriceService.class);
    parkingService = new ParkingService(spotService, priceService);
    doReturn(12.0).when(priceService).getExitPrice(any());

  }

  @BeforeEach
  void reInit() {
    Date now = new Date();
    long nowMinus1Hour = now.getTime() - 60 * 60 * 1000;
    enteringTime = new Date(nowMinus1Hour);
    ticket.setEnteringTime(enteringTime);
  }


  @Test
  void givenNoFreeSpot_processIncomingVehicle_failsWithNoMoreSpotException() {
    when(spotService.spotsAvailable(parking)).thenReturn(false);
    assertThatExceptionOfType(NoMoreSpotException.class)
        .isThrownBy(() -> parkingService.processIncomingVehicle(vehicle, parking));

  }

  @Test
  void givenSomeFreeSpotInParking_processIncomingVehicle_generatesATicket() {
    when(spotService.spotsAvailable(parking)).thenReturn(true);
    assertThatNoException().isThrownBy(() -> parkingService.processIncomingVehicle(vehicle, parking));
  }


  @Test
  void givenAVehicle_processIncomingVehicle_generatesTicketWithRightTime() throws NoMoreSpotException {
    //given
    Date now = new Date();
    when(spotService.spotsAvailable(parking)).thenReturn(true);

    //when : Enregistrer le véhicule
    Ticket ticket = parkingService.processIncomingVehicle(vehicle, parking);

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

  //Soit un véhicule enregistré dans le parking

  //faire sortir ce véhicule

  // engendre la définition d'une heure de sortie > l'heure d'entrée et d'un prix , à associer au ticket de sortie

  @Test
  void givenARegisteredVehicle_processExitingVehicle_generatesWithHourAndPrice() {

    //when
    Ticket exitTicket = parkingService.processExitingVehicle(ticket);

    //then
    assertThat(exitTicket).isNotNull();
    Date exitTime = exitTicket.getExitTime();
    assertThat(exitTime).isNotNull();
    assertThat(exitTime).isAfter(enteringTime);
    assertThat(exitTicket.getAmount()).isNotEqualTo(0);

  }

  // Soit un véhicule,   enregistré dans le parking

  //faire sortir ce véhicule

  // engendre la définition du prix approprié selon les règle suivantes:
  //0.08 € /min pour une CITADINE
  //0.02 € /min pour un BICYCLE (2 Roues)
  //0.05 € /min pour un type inconnu (choix par défaut)

  @Test
  //@Disabled("Move to another test class")
  void givenARegisteredVehicle_processExitingVehicle_generatesTheRightPrice() {

    //given
    Vehicle car = new Car();
    car.setRegistrationNumber(CAR_REGISTRATION_NUMBER);

    Vehicle bicycle = new Bicycle();
    bicycle.setRegistrationNumber(BICYCLE_REGISTRATION_NUMBER);

    Ticket carTicket = new Ticket();
    carTicket.setVehicle(car);

    Ticket bicycleTicket = new Ticket();
    bicycleTicket.setVehicle(bicycle);

    ticket.setEnteringTime(enteringTime);
    carTicket.setEnteringTime(enteringTime);
    bicycleTicket.setEnteringTime(enteringTime);
    when(priceService.getExitPrice(any())).thenCallRealMethod();

    //when
    Ticket exitVehicleTicket = parkingService.processExitingVehicle(ticket);
    Ticket exitCarTicket = parkingService.processExitingVehicle(carTicket);
    Ticket exitBicycleTicket = parkingService.processExitingVehicle(bicycleTicket);

    //then
    assertThat(exitVehicleTicket).isNotNull();
    assertThat(exitCarTicket).isNotNull();
    assertThat(exitBicycleTicket).isNotNull();

    assertThat(exitVehicleTicket.getAmount()).isEqualTo(3);
    assertThat(exitCarTicket.getAmount()).isEqualTo(4.8);
    assertThat(exitBicycleTicket.getAmount()).isEqualTo(1.2);

  }


}
