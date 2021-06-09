package com.zerofiltre.parkingbot.model;

import java.util.Date;

public class Ticket {

  private String amount;
  private Vehicle vehicle;
  private Date enteringTime;
  private Date exitTime;

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public Date getEnteringTime() {
    return enteringTime;
  }

  public void setEnteringTime(Date enteringTime) {
    this.enteringTime = enteringTime;
  }

  public Date getExitTime() {
    return exitTime;
  }

  public void setExitTime(Date exitTime) {
    this.exitTime = exitTime;
  }


}
