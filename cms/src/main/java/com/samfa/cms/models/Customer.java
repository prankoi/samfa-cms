package com.samfa.cms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer customerId;
  public String firstName;
  public String lastName;
  public String emailAddress;
	public String cellphoneNumber;
  public String password;
  
	public Customer() {}
	
	public Customer(Integer customerId, String firstName, String lastName, String emailAddress, String cellphoneNumber, String password) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.cellphoneNumber = cellphoneNumber;
		this.password = password;
	}
	
  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
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
