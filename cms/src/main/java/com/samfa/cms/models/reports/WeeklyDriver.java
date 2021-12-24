package com.samfa.cms.models.reports;

public class WeeklyDriver extends Weekly {
	public Integer driverId;
	
	public WeeklyDriver() {}
	
	public WeeklyDriver(Integer driverId, String startDate, String endDate, Integer bookingFee, Integer totalIncome, Integer totalBookings) {
		this.driverId = driverId;
		this.setStartDate(startDate);
		this.setEndDate(endDate);
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