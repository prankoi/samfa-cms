package com.samfa.cms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Driver {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer driverId;
	public Integer vehicleId;
	public String vehicleDetails;
  public String firstName;
  public String lastName;
  public String emailAddress;
	public String cellphoneNumber;
  public String password;
  
	public Driver() {}
	
	public Driver(Integer driverId, Integer vehicleId, String vehicleDetails, String firstName, String lastName, String emailAddress,
		String cellphoneNumber, String password) {
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.vehicleDetails = vehicleDetails;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.cellphoneNumber = cellphoneNumber;
		this.password = password;
	}
	
  public Integer getDriverId() {
    return driverId;
  }

  public void setDriverId(Integer driverId) {
    this.driverId = driverId;
  }
	
	public Integer getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Integer vehicleId) {
    this.vehicleId = vehicleId;
  }
	
	public String getVehicleDetails() {
    return vehicleDetails;
  }

  public void setVehicleDetails(String vehicleDetails) {
    this.vehicleDetails = vehicleDetails;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

	public String getCellphoneNumber() {
    return cellphoneNumber;
  }

  public void setCellphoneNumber(String cellphoneNumber) {
    this.cellphoneNumber = cellphoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
