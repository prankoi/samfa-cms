package com.samfa.cms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Booking {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public String referenceNumber;
  public Integer customerId;
	public Integer driverId;
	public Integer vehicleId;
	public String confirmationDate;
	public String bookingDate;
	public String fulfillmentDate;
	public String pickupLocation;
	public String dropoffLocation;
	public String landmarks;
	public Integer bookingFee;
	public Integer serviceFee;
	public Integer amountPaid;
	public Integer promoId;
	public Integer paymentId;
	public String status;
	public Integer distance;
	
	public Booking() {}
	
	public Booking(String referenceNumber, Integer customerId, Integer driverId, Integer vehicleId, String confirmationDate,
		String bookingDate, String fulfillmentDate, String pickupLocation, String dropoffLocation, String landmarks, Integer bookingFee,
		Integer serviceFee, Integer amountPaid, Integer promoId, Integer paymentId, String status, Integer distance) {
		this.referenceNumber = referenceNumber;
		this.customerId = customerId;
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.confirmationDate = confirmationDate;
		this.bookingDate = bookingDate;
		this.fulfillmentDate = fulfillmentDate;
		this.pickupLocation = pickupLocation;
		this.dropoffLocation = dropoffLocation;
		this.landmarks = landmarks;
		this.bookingFee = bookingFee;
		this.serviceFee = serviceFee;
		this.amountPaid = amountPaid;
		this.promoId = promoId;
		this.paymentId = paymentId;
		this.status = status;
		this.distance = distance;
	}
	
  public String getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }
	
	public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
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

	public String getConfirmationDate() {
    return confirmationDate;
  }

  public void setConfirmationDate(String confirmationDate) {
    this.confirmationDate = confirmationDate;
  }
	
	public String getBookingDate() {
    return bookingDate;
  }

  public void setBookingDate(String bookingDate) {
    this.bookingDate = bookingDate;
  }

  public String getFulfillmentDate() {
    return fulfillmentDate;
  }

  public void setFulfillmentDate(String fulfillmentDate) {
    this.fulfillmentDate = fulfillmentDate;
  }
	
	public String getPickupLocation() {
    return pickupLocation;
  }

  public void setPickupLocation(String pickupLocation) {
    this.pickupLocation = pickupLocation;
  }

	public String getDropoffLocation() {
    return dropoffLocation;
  }

  public void setDropoffLocation(String dropoffLocation) {
    this.dropoffLocation = dropoffLocation;
  }

  public String getLandmarks() {
    return landmarks;
  }

  public void setLandmarks(String landmarks) {
    this.landmarks = landmarks;
  }
	
	public Integer getBookingFee() {
    return bookingFee;
  }

  public void setBookingFee(Integer bookingFee) {
    this.bookingFee = bookingFee;
  }
	public Integer getServiceFee() {
    return serviceFee;
  }

  public void setServiceFee(Integer serviceFee) {
    this.serviceFee = serviceFee;
  }
	public Integer getAmountPaid() {
    return amountPaid;
  }

  public void setAmountPaid(Integer amountPaid) {
    this.amountPaid = amountPaid;
  }
	public Integer getPromoId() {
    return promoId;
  }

  public void setPromoId(Integer promoId) {
    this.promoId = promoId;
  }
	public Integer getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(Integer paymentId) {
    this.paymentId = paymentId;
  }
	
	public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
	
	public Integer getDistance() {
    return distance;
  }

  public void setDistance(Integer distance) {
    this.distance = distance;
  }
}
