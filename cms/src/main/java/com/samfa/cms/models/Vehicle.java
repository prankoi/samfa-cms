package com.samfa.cms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Vehicle {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
	
  private Integer vehicleId;
  public String plateNumber;
  public String brand;
  public String model;
	public Integer passengerCapacity;
  public Integer loadCapacity;
	public String status;
	public String assignability;
  
	public Vehicle() {}
	
	public Vehicle(Integer vehicleId, String plateNumber, String brand, String model, Integer passengerCapacity, Integer loadCapacity, String status, String assignability) {
		this.vehicleId = vehicleId;
		this.plateNumber = plateNumber;
		this.brand = brand;
		this.model = model;
		this.passengerCapacity = passengerCapacity;
		this.loadCapacity = loadCapacity;
		this.status = status;
		this.assignability = assignability;
	}
	
  public Integer getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Integer vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getPlateNumber() {
    return plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

	public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }
	
	public Integer getPassengerCapacity() {
    return passengerCapacity;
  }

  public void setPassengerCapacity(Integer passengerCapacity) {
    this.passengerCapacity = passengerCapacity;
  }


 public Integer getLoadCapacity() {
    return loadCapacity;
  }

  public void setLoadCapacity(Integer loadCapacity) {
    this.loadCapacity = loadCapacity;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
	
	public String getAssignability() {
    return assignability;
  }

  public void setAssignability(String assignability) {
    this.assignability = assignability;
  }
}
