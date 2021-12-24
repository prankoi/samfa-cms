package com.samfa.cms.models.reports;

public class DailyDriver extends Daily {
	
	public Integer driverId;
	
	public DailyDriver() {}
	
	public DailyDriver(Integer driverId, String confirmationDate, Integer bookingFee, Integer totalIncome, Integer totalBookings) {
		this.driverId = driverId;
		this.setConfirmationDate(confirmationDate);
		this.setBookingFee(bookingFee);
		this.setTotalIncome(totalIncome);
		this.setTotalBookings(totalBookings);
	}
	
	public Integer getDriverId() {
		return driverId;
	}
	
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
}