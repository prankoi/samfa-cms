package com.samfa.cms.models;

import javax.persistence.Entity;

@Entity
public class Delivery extends Booking {

	public Integer itemId;
	public String recipientName = "";
	public String recipientContact = "";

	public Delivery() {}
	
	public Delivery(String referenceNumber, Integer customerId, Integer driverId, Integer vehicleId, String confirmationDate,
		String bookingDate, String fulfillmentDate, String pickupLocation, String dropoffLocation, String landmarks, Integer itemId,
		String recipientName, String recipientContact, Integer bookingFee, Integer serviceFee, Integer amountPaid, Integer promoId,
		Integer paymentId, String status, Integer distance) {
		this.setReferenceNumber(referenceNumber);
		this.setCustomerId(customerId);
		this.setDriverId(driverId);
		this.setVehicleId(vehicleId);
		this.setConfirmationDate(confirmationDate);
		this.setBookingDate(bookingDate);
		this.setFulfillmentDate(fulfillmentDate);
		this.setPickupLocation(pickupLocation);
		this.setDropoffLocation(dropoffLocation);
		this.setLandmarks(landmarks);
		this.itemId = itemId;
		this.recipientName = recipientName;
		this.recipientContact = recipientContact;
		this.setBookingFee(bookingFee);
		this.setServiceFee(serviceFee);
		this.setAmountPaid(amountPaid);
		this.setPromoId(promoId);
		this.setPaymentId(paymentId);
		this.setStatus(status);
		this.setDistance(distance);
	}
	
	public Integer getItemId() {
		return itemId;
	}
	
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	public String getRecipientName() {
		return recipientName;
	}
	
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	
	public String getRecipientContact() {
		return recipientContact;
	}
	
	public void setRecipientContact(String recipientContact) {
		this.recipientContact = recipientContact;
	}
}