package com.samfa.cms.models;

import javax.persistence.Entity;

@Entity
public class Trip extends Booking {
	
	public Trip() {}
	
	public Trip(String referenceNumber, Integer customerId, Integer driverId, Integer vehicleId, String confirmationDate,
		String bookingDate, String fulfillmentDate, String pickupLocation, String dropoffLocation, String landmarks,
		Integer bookingFee, Integer serviceFee, Integer amountPaid, Integer promoId, Integer paymentId, String status,
		Integer distance) {
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
		this.setBookingFee(bookingFee);
		this.setServiceFee(serviceFee);
		this.setAmountPaid(amountPaid);
		this.setPromoId(promoId);
		this.setPaymentId(paymentId);
		this.setStatus(status);
		this.setDistance(distance);
	}
}